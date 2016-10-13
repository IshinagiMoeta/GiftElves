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
import com.bystanders.moeta.giftelves.activity.GiftActivity;
import com.bystanders.moeta.giftelves.bean.GiftBean;
import com.bystanders.moeta.giftelves.callback.GiftListCallBack;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ishinagi_moeta on 2016/9/29.
 */
public class GiftAdapter extends BaseAdapter {

    List<GiftBean.ListBean> listBeen = new ArrayList<>();
    Context context;
    public static final String PATH = "http://www.1688wan.com";
    GiftListCallBack callBack;

    public void setCallBack(GiftListCallBack callBack) {
        this.callBack = callBack;
    }

    public GiftAdapter(List<GiftBean.ListBean> listBeen, Context context) {
        this.listBeen = listBeen;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listBeen.size();
    }

    @Override
    public GiftBean.ListBean getItem(int position) {
        return listBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.gift_list_item,null);
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        GiftBean.ListBean bean = listBeen.get(position);
        viewHolder.tv_title.setText(bean.getGname());
        viewHolder.tv_type.setText(bean.getGiftname());
        viewHolder.tv_num.setText(String.valueOf(bean.getNumber()));
        viewHolder.tv_data.setText(bean.getAddtime());

        Picasso.with(context)
                .load(PATH+bean.getIconurl())
                .into(viewHolder.img);
        if (bean.getNumber()!=0){
            viewHolder.button.setText("立刻领取");
        }else{
            viewHolder.button.setText("淘号");
        }



        viewHolder.button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GiftActivity.class);
                intent.putExtra("giftid", listBeen.get(position).getId());
                intent.putExtra("giftname", listBeen.get(position).getGiftname());
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.gift_list_item_title)
        TextView tv_title;
        @BindView(R.id.gift_list_item_num)
        TextView tv_num;
        @BindView(R.id.gift_list_item_type)
        TextView tv_type;
        @BindView(R.id.gift_list_item_data)
        TextView tv_data;
        @BindView(R.id.gift_list_item_image)
        ImageView img;
        @BindView(R.id.gift_list_item_button)
        Button button;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }
}
