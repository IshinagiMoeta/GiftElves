package com.bystanders.moeta.giftelves.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bystanders.moeta.giftelves.R;
import com.bystanders.moeta.giftelves.activity.GameActivity;
import com.bystanders.moeta.giftelves.bean.WeeklyListBean;
import com.bystanders.moeta.giftelves.utils.ContextUtils;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ishinagi_moeta on 2016/10/13.
 */
public class WeeklyListAdapter extends BaseAdapter {
    Context context;
    WeeklyListBean bean;

    public WeeklyListAdapter(Context context, WeeklyListBean bean) {
        this.context = context;
        this.bean = bean;
    }

    @Override
    public int getCount() {
        return bean.getList().size();
    }

    @Override
    public Object getItem(int position) {
        return bean.getList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.weekylist_item,null);
            viewHolder = new ViewHolder(convertView);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_title.setText(bean.getList().get(position).getAppname());
        viewHolder.tv_size.setText(bean.getList().get(position).getAppsize());
        viewHolder.tv_descs.setText(bean.getList().get(position).getDescs());
        viewHolder.tv_type.setText(bean.getList().get(position).getTypename());

        Picasso.with(context).load(ContextUtils.PATH+bean.getList().get(position).getIconurl()).into(viewHolder.imageView);
        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GameActivity.class);
                intent.putExtra("gameid", bean.getList().get(position).getAppid());
                intent.putExtra("imgurl",bean.getList().get(position).getIconurl());
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    class ViewHolder{
        @BindView(R.id.weeklylist_img)
        ImageView imageView;
        @BindView(R.id.weeklylist_title)
        TextView tv_title;
        @BindView(R.id.weeklylist_size)
        TextView tv_size;
        @BindView(R.id.weeklylist_type)
        TextView tv_type;
        @BindView(R.id.weeklylist_descs)
        TextView tv_descs;
        @BindView(R.id.weeklylist_button)
        Button button;
        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
            view.setTag(this);
        }
    }
}
