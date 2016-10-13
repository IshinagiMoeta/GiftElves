package com.bystanders.moeta.giftelves.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bystanders.moeta.giftelves.R;
import com.bystanders.moeta.giftelves.bean.WednesdayBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ishinagi_moeta on 2016/10/9.
 */
public class WednesdayAdapter extends BaseAdapter {

    List<WednesdayBean.ListBean> list;
    Context context;

    public WednesdayAdapter(List<WednesdayBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public WednesdayBean.ListBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.wednesday_list_item,null);
            viewHolder = new ViewHolder(convertView);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        WednesdayBean.ListBean listBean = list.get(position);
        viewHolder.tv_title.setText(listBean.getName());
        viewHolder.tv_time.setText(listBean.getAddtime());
        Picasso.with(context).load("http://www.1688wan.com/"+listBean.getIconurl()).into(viewHolder.imageView);
        return convertView;
    }

    class ViewHolder{
        @BindView(R.id.wednesday_title)
        TextView tv_title;
        @BindView(R.id.wednesday_time)
        TextView tv_time;
        @BindView(R.id.wednesday_image)
        ImageView imageView;

        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
            view.setTag(this);
        }
    }
}
