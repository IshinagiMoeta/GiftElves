package com.bystanders.moeta.giftelves.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Ishinagi_moeta on 2016/9/28.
 */
public class AdAdapter extends PagerAdapter {

    List<View> list;

    public AdAdapter(List<View> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * 初始化ViewPager容器中的页page
     *
     * @param container:代指的ViewPager
     * @param position:根据指定创建新的View
     * @return:创建的新的Object
     */
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        //根据指定pos位置取出View
        View view = list.get(position);
        //把View添加到ViewGroup容器中
        container.addView(view);
        return view;
    }

    //根据指定位置把不需要的内容移除掉
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super调用父类，父类直接抛出异常让自己处理
        // super.destroyItem(container, position, object);
        container.removeView(list.get(position));
    }
}
