package com.bystanders.moeta.giftelves.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bystanders.moeta.giftelves.R;
import com.bystanders.moeta.giftelves.adapter.WeeklyListAdapter;
import com.bystanders.moeta.giftelves.bean.WeeklyListBean;
import com.bystanders.moeta.giftelves.utils.ContextUtils;
import com.bystanders.moeta.giftelves.utils.GsonUtils;
import com.bystanders.moeta.giftelves.utils.OKHttpUtils;
import com.bystanders.moeta.giftelves.view.CustomListView;
import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeeklyActivity extends AppCompatActivity {

    @BindView(R.id.weekly_title)
    TextView tv_title;
    @BindView(R.id.weekly_descs)
    TextView tv_descs;
    @BindView(R.id.weekly_author)
    RoundedImageView img_author;
    @BindView(R.id.weekly_share)
    ImageView img_share;
    @BindView(R.id.weekly_back)
    ImageView img_back;
    @BindView(R.id.weekly_img_bg)
    ImageView img_bg;
    @BindView(R.id.weekly_listview)
    CustomListView listView;

    WeeklyListBean bean;
    WeeklyListAdapter adapter;

    String name;
    String iconurl;
    String descs;
    String author;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                String str = (String) msg.obj;
                Gson gson = GsonUtils.getGson();

                bean = gson.fromJson(str,WeeklyListBean.class);

                tv_title.setText(name);
                tv_descs.setText(descs);
                Picasso.with(WeeklyActivity.this).load(ContextUtils.PATH+"/"+author).into(img_author);
                Picasso.with(WeeklyActivity.this).load(ContextUtils.PATH+iconurl).into(img_bg);
                adapter = new WeeklyListAdapter(WeeklyActivity.this,bean);
                listView.setAdapter(adapter);
                listView.setListViewHeightBasedOnChildren(listView);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        iconurl = intent.getStringExtra("iconurl");
        descs = intent.getStringExtra("descs");
        author = intent.getStringExtra("author");

        OKHttpUtils.startOkhttp(handler,0, ContextUtils.WEEKLYLIST+id);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        img_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WeeklyActivity.this,"分享功能正在开发中",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
