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
import com.bystanders.moeta.giftelves.bean.WednesdayGridBean;
import com.bystanders.moeta.giftelves.utils.ContextUtils;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ishinagi_moeta on 2016/10/13.
 */
public class WednesdayGridAdapter extends BaseAdapter{
    Context context;
    WednesdayGridBean bean;

    public WednesdayGridAdapter(Context context, WednesdayGridBean bean) {
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
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.wednesdaygrid_item,null);
            viewHolder = new ViewHolder(convertView);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_title.setText(bean.getList().get(position).getAppname());
        Picasso.with(context).load(ContextUtils.PATH+bean.getList().get(position).getAppicon()).into(viewHolder.imageView);
        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GameActivity.class);
                intent.putExtra("gameid", bean.getList().get(position).getAppid());
                intent.putExtra("imgurl",bean.getList().get(position).getAppicon());
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    class ViewHolder{
        @BindView(R.id.wednesday_grid_icon)
        ImageView imageView;
        @BindView(R.id.wednesday_grid_title)
        TextView tv_title;
        @BindView(R.id.wednesday_grid_button)
        Button button;


        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
            view.setTag(this);
        }
    }
}
