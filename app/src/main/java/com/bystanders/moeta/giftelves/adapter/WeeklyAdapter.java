package com.bystanders.moeta.giftelves.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bystanders.moeta.giftelves.R;
import com.bystanders.moeta.giftelves.bean.WeeklyBean;
import com.bystanders.moeta.giftelves.utils.ContextUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ishinagi_moeta on 2016/10/9.
 */
public class WeeklyAdapter extends BaseAdapter{
    List<WeeklyBean.ListBean> listBeen;
    Context context;

    public WeeklyAdapter(List<WeeklyBean.ListBean> listBeen, Context context) {
        this.listBeen = listBeen;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listBeen.size();
    }

    @Override
    public WeeklyBean.ListBean getItem(int position) {
        return listBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.weekly_list_item,null);
            viewHolder = new ViewHolder(convertView);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        WeeklyBean.ListBean bean = listBeen.get(position);
        viewHolder.tv_title.setText(bean.getName());
        Picasso.with(context).load(ContextUtils.PATH + bean.getOriiconurl()).into(viewHolder.tv_bg);
        Picasso.with(context).load(ContextUtils.PATH +"/"+ bean.getAuthorimg()).into(viewHolder.tv_user);
        return convertView;
    }

    class ViewHolder{

        @BindView(R.id.weekly_list_title)
        TextView tv_title;
        @BindView(R.id.weekly_list_image)
        RoundedImageView tv_user;
        @BindView(R.id.weekly_list_bg)
        ImageView tv_bg;

        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
            view.setTag(this);
        }
    }
}
