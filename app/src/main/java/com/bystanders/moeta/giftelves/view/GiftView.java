package com.bystanders.moeta.giftelves.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import com.bystanders.moeta.giftelves.R;
import com.bystanders.moeta.giftelves.adapter.AdAdapter;
import com.bystanders.moeta.giftelves.bean.GiftBean;
import com.bystanders.moeta.giftelves.utils.AdUtils;

import java.util.ArrayList;
import java.util.List;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

/**
 * Created by Ishinagi_moeta on 2016/9/28.
 */
public class GiftView extends ScrollView {

    public static final String PATH = "http://www.1688wan.com";
    GiftBean giftBean;
    Context context;

    public void setGiftBean(GiftBean giftBean) {
        this.giftBean = giftBean;
    }

    public GiftView(Context context) {
        this(context,null);
    }

    public GiftView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public GiftView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public void initView(){
        addHintHead();
        addAdViewPage();
    }

    private void addAdViewPage() {
        View view = LayoutInflater.from(context).inflate(R.layout.ad_head, null, false);
        AutoScrollViewPager viewPager = (AutoScrollViewPager) view.findViewById(R.id.ad_head_vp);
        final LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ad_head_ponit_container);

        List<GiftBean.AdBean> ad = giftBean.getAd();
        List<String> list = new ArrayList<>();
        for (int i = 0;i<ad.size();i++){
            String imgUrl = PATH+ad.get(i).getIconurl();
            list.add(imgUrl);
        }
        List<View> viewList = AdUtils.initAd(giftBean,context,linearLayout);
        AdAdapter adapter = new AdAdapter(viewList);
        //填充页面
        viewPager.setAdapter(adapter);
        //开启自动轮播效果
        viewPager.startAutoScroll();
        //设置边界切换的动画
        viewPager.setBorderAnimation(true);
        //设置循环轮播
        viewPager.setCycle(true);
        //两个页面之间切换间隔的时间
        viewPager.setInterval(4000);
        //设置切换的方向
        viewPager.setDirection(AutoScrollViewPager.RIGHT);
        //当手触摸的时候停止滚动
        viewPager.setStopScrollWhenTouch(true);

        //ViewPager页面切换的监听
        //该方法被抛弃，使用addOnPageChangeListener替代
        //viewPager.setOnPageChangeListener();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //页面滚动过程
                /**
                 * position:当positionOffset非0表示加载下一页，position+1
                 * positionOffset:0-1(0-100%)为0表示不动，大于0表示页面在滑动
                 * positionOffsetPixels:从当前页面移动的像素值
                 */
                // Log.e("====","==position==="+position+"==positionOffset=="+positionOffset+"=positionOffsetPixels==="+positionOffsetPixels);

            }

            @Override
            public void onPageSelected(int position) {

                //选中那个页面
                int count = linearLayout.getChildCount();
                for (int i = 0; i < count; i++) {
                    ImageView img = (ImageView) linearLayout.getChildAt(i);
                    //0,1,2,3,4

                    if (i == position) {
                        //表示选中
                        img.setImageResource(R.drawable.point_selected);
                    } else {
                        //未选中
                        img.setImageResource(R.drawable.point_default);
                    }

                }

            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void addHintHead() {
        View view = LayoutInflater.from(context).inflate(R.layout.hint_head, null, false);
        addView(view);
    }

    class MyListView extends ListView{

        public MyListView(Context context) {
            super(context);
        }

        public MyListView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec,
                    MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                            MeasureSpec.AT_MOST));
        }
    }
}
