package com.bystanders.moeta.giftelves.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bystanders.moeta.giftelves.R;
import com.bystanders.moeta.giftelves.bean.HotBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ishinagi_moeta on 2016/10/9.
 */
public class HotListAdapter extends BaseAdapter {
    List<HotBean.InfoBean.Push1Bean> push1;
    Context context;

    public HotListAdapter(List<HotBean.InfoBean.Push1Bean> push1, Context context) {
        this.push1 = push1;
        this.context = context;
    }

    @Override
    public int getCount() {
        return push1.size();
    }

    @Override
    public HotBean.InfoBean.Push1Bean getItem(int position) {
        return push1.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.hot_list_item,null);
            viewHolder = new ViewHolder(convertView);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        HotBean.InfoBean.Push1Bean bean = push1.get(position);
        viewHolder.tv_num.setText(String.valueOf(bean.getClicks()));
        viewHolder.tv_size.setText("游戏大小:"+bean.getSize());
        viewHolder.tv_title.setText(bean.getName());
        viewHolder.tv_type.setText(bean.getTypename());
        Picasso.with(context).load("http://www.1688wan.com"+bean.getLogo()).into(viewHolder.imageView);
        return convertView;
    }

    class ViewHolder{
        @BindView(R.id.hot_list_title)
        TextView tv_title;
        @BindView(R.id.hot_list_num)
        TextView tv_num;
        @BindView(R.id.hot_list_size)
        TextView tv_size;
        @BindView(R.id.hot_list_type)
        TextView tv_type;
        @BindView(R.id.hot_list_image)
        ImageView imageView;

        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
            view.setTag(this);
        }
    }
}
