package com.bystanders.moeta.giftelves.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bystanders.moeta.giftelves.R;
import com.bystanders.moeta.giftelves.adapter.WednesdayGridAdapter;
import com.bystanders.moeta.giftelves.bean.WednesdayGridBean;
import com.bystanders.moeta.giftelves.utils.ContextUtils;
import com.bystanders.moeta.giftelves.utils.GsonUtils;
import com.bystanders.moeta.giftelves.utils.OKHttpUtils;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WednesdayActivity extends AppCompatActivity {

    @BindView(R.id.wednesday_title)
    TextView tv_title;
    @BindView(R.id.wednesday_data)
    TextView tv_data;
    @BindView(R.id.wednesday_descs)
    TextView tv_descs;

    @BindView(R.id.wednesday_back)
    ImageView img_back;
    @BindView(R.id.wednesday_share)
    ImageView img_share;
    @BindView(R.id.wednesday_img_bg)
    ImageView img_bg;

    @BindView(R.id.wednesday_grid)
    GridView gridView;

    WednesdayGridAdapter adapter;


    WednesdayGridBean bean;
    String name;
    String iconurl;
    String descs;
    String addtime;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                String str = (String) msg.obj;
                Gson gson = GsonUtils.getGson();
                bean = gson.fromJson(str,WednesdayGridBean.class);

                tv_title.setText(name);
                tv_descs.setText(Html.fromHtml("<b><font color=\"#000000\">导读：</font></b>"+descs));
                tv_data.setText(addtime);
                Picasso.with(WednesdayActivity.this).load(ContextUtils.PATH+iconurl).into(img_bg);

                adapter = new WednesdayGridAdapter(WednesdayActivity.this,bean);
                gridView.setAdapter(adapter);
                setListViewHeightBasedOnChildren(gridView);
                findViewById(R.id.wednesday_scrollview).scrollTo(10, 10);

            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wednesday);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        String sid = intent.getStringExtra("sid");
        name = intent.getStringExtra("name");
        iconurl = intent.getStringExtra("iconurl");
        descs = intent.getStringExtra("descs");
        addtime = intent.getStringExtra("addtime");

        OKHttpUtils.startOkhttp(handler,0, ContextUtils.WEDNESDAYGRID+sid);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        img_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WednesdayActivity.this,"分享功能正在开发中",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public static void setListViewHeightBasedOnChildren(GridView listView) {
        // 获取listview的adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        // 固定列宽，有多少列
        int col = 3;// listView.getNumColumns();
        int totalHeight = 0;
        // i每次加4，相当于listAdapter.getCount()小于等于4时 循环一次，计算一次item的高度，
        // listAdapter.getCount()小于等于8时计算两次高度相加
        for (int i = 0; i < listAdapter.getCount(); i += col) {
            // 获取listview的每一个item
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            // 获取item的高度和
            totalHeight += listItem.getMeasuredHeight();
        }

        // 获取listview的布局参数
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        // 设置高度
        params.height = totalHeight;
        // 设置margin
        ((ViewGroup.MarginLayoutParams) params).setMargins(10, 10, 10, 10);
        // 设置参数
        listView.setLayoutParams(params);
    }
}
