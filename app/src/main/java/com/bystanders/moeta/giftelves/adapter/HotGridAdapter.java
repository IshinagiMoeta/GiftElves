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
public class HotGridAdapter extends BaseAdapter {
    List<HotBean.InfoBean.Push2Bean> push2;
    Context context;

    public HotGridAdapter(List<HotBean.InfoBean.Push2Bean> push2, Context context) {
        this.push2 = push2;
        this.context = context;
    }

    @Override
    public int getCount() {
        return push2.size();
    }

    @Override
    public HotBean.InfoBean.Push2Bean getItem(int position) {
        return push2.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.hot_grid_item,null);
            viewHolder = new ViewHolder(convertView);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_title.setText(push2.get(position).getName());
        Picasso.with(context).load("http://www.1688wan.com"+push2.get(position).getLogo()).into(viewHolder.imageView);
        return convertView;
    }

    class ViewHolder{
        @BindView(R.id.hot_grid_image)
        ImageView imageView;
        @BindView(R.id.hot_grid_title)
        TextView tv_title;

        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
            view.setTag(this);
        }
    }
}
