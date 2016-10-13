package com.bystanders.moeta.giftelves.utils;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bystanders.moeta.giftelves.R;
import com.bystanders.moeta.giftelves.activity.GiftActivity;
import com.bystanders.moeta.giftelves.bean.GiftBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bluesky on 16/9/8.
 */
public class AdUtils {
    /**
     *
     * 动态添加广告的页数以及相关指示器圆点
     * @param imgs:需要加载的图片的地址
     * @param context:上下文对象
     * @param layout_point_container:与图片对应的指示器圆点的容器布局
     * @return:返回ViewPager要加载的View集合对象
     */
    public static List<View>  initAd(final GiftBean imgs, final Context context, LinearLayout layout_point_container){

        List<View> viewList = new ArrayList<>();

        for (int i = 0; i < imgs.getAd().size(); i++) {
            //显示图片
            ImageView img = new ImageView(context);
            //下载图片并显示
            Picasso.with(context).load(ContextUtils.PATH+imgs.getAd().get(i).getIconurl())
                    .placeholder(R.mipmap.ic_launcher)
                    .into(img);

            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            final int pos = i;
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, GiftActivity.class);
                    intent.putExtra("giftid", imgs.getAd().get(pos).getGiftid());
                    intent.putExtra("giftname", imgs.getAd().get(pos).getTitle());
                    context.startActivity(intent);
                }
            });
            viewList.add(img);

            //显示圆点
            ImageView img_point = new ImageView(context);
            //设置每个imageView的id
            img_point.setId(i);
            //设置圆点之间的距离
            img_point.setPadding(10, 0, 10, 0);
            //设置圆点内容显示
            //默认选中第一个
            if (i == 0) {
                //选中
                img_point.setImageResource(R.drawable.point_selected);
            } else {
                //不选中
                img_point.setImageResource(R.drawable.point_default);
            }
            //添加圆点到容器中并显示
            layout_point_container.addView(img_point);

        }
        return  viewList;

    }

}
