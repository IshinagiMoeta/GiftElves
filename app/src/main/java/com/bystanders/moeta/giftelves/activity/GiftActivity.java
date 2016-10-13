package com.bystanders.moeta.giftelves.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bystanders.moeta.giftelves.R;
import com.bystanders.moeta.giftelves.bean.GiftInfoBean;
import com.bystanders.moeta.giftelves.utils.ContextUtils;
import com.bystanders.moeta.giftelves.utils.GsonUtils;
import com.bystanders.moeta.giftelves.utils.OKHttpUtils;
import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GiftActivity extends AppCompatActivity {

    @BindView(R.id.giftactivity_title)
    TextView tv_title;
    @BindView(R.id.giftactivity_data)
    TextView tv_data;
    @BindView(R.id.giftactivity_num)
    TextView tv_num;
    @BindView(R.id.giftactivity_descs)
    TextView tv_descs;
    @BindView(R.id.giftactivity_explains)
    TextView tv_explains;
    @BindView(R.id.giftactivity_back)
    ImageView img_back;
    @BindView(R.id.giftactivity_share)
    ImageView img_share;
    @BindView(R.id.giftactivity_user)
    RoundedImageView img_user;
    @BindView(R.id.giftactivity_button)
    Button button;
    @BindView(R.id.giftactivity_gift)
    ImageView img_gift;

    GiftInfoBean bean;
    String giftName;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    initView(msg);
                    break;
            }
        }
    };

    private void initView(Message msg) {
        String str = (String) msg.obj;
        Gson gson = GsonUtils.getGson();

        bean = gson.fromJson(str,GiftInfoBean.class);
        GiftInfoBean.InfoBean beanInfo = bean.getInfo();
        Picasso.with(this).load(ContextUtils.PATH+beanInfo.getIconurl()).into(img_user);
        tv_title.setText(giftName);
        tv_data.setText("有效期:"+beanInfo.getOvertime());
        tv_num.setText(String.valueOf(beanInfo.getExchanges()));
        tv_descs.setText(beanInfo.getDescs());
        tv_explains.setText(beanInfo.getExplains());
        if (beanInfo.getExchanges()==0){
            button.setText("淘号");
            button.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String giftId = intent.getStringExtra("giftid");
        giftName = intent.getStringExtra("giftname");
        OKHttpUtils.startOkhttp(handler,0,ContextUtils.GIFTINFO+giftId);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        img_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GiftActivity.this,"分享功能正在开发中",Toast.LENGTH_SHORT).show();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(GiftActivity.this,LoginActivity.class);
                startActivity(intent1);
            }
        });
        img_gift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(GiftActivity.this,LoginActivity.class);
                startActivity(intent1);
            }
        });
    }
}
