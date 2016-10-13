package com.bystanders.moeta.giftelves.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bystanders.moeta.giftelves.R;
import com.bystanders.moeta.giftelves.bean.GameBean;
import com.bystanders.moeta.giftelves.utils.ContextUtils;
import com.bystanders.moeta.giftelves.utils.GsonUtils;
import com.bystanders.moeta.giftelves.utils.OKHttpUtils;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameActivity extends AppCompatActivity {

    @BindView(R.id.gameactivity_title)
    TextView tv_title;
    @BindView(R.id.gameactivity_gamename)
    TextView tv_name;
    @BindView(R.id.gameactivity_size)
    TextView tv_size;
    @BindView(R.id.gameactivity_type)
    TextView tv_type;
    @BindView(R.id.gameactivity_info)
    TextView tv_info;

    @BindView(R.id.gameactivity_icon)
    ImageView img_icon;
    @BindView(R.id.gameactivity_scroll_img1)
    ImageView img_1;
    @BindView(R.id.gameactivity_scroll_img2)
    ImageView img_2;
    @BindView(R.id.gameactivity_scroll_img3)
    ImageView img_3;
    @BindView(R.id.gameactivity_scroll_img4)
    ImageView img_4;
    @BindView(R.id.gameactivity_scroll_img5)
    ImageView img_5;


    @BindView(R.id.gameactivity_back)
    ImageView img_back;
    @BindView(R.id.gameactivity_share)
    ImageView img_share;

    @BindView(R.id.gameactivity_button)
    Button button;

    GameBean bean;
    String imgUrl;
    String downloadUrl = null;

    protected Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                initView(msg);

            }
        }
    };

    private void initView(Message msg) {
        String str = (String) msg.obj;
        Gson gson = GsonUtils.getGson();
        bean = gson.fromJson(str, GameBean.class);
        GameBean.AppBean appBean = bean.getApp();
        tv_title.setText(appBean.getName());
        tv_name.setText(appBean.getName());
        if (!TextUtils.isEmpty(appBean.getAppsize())) {
            tv_size.setText("大小:" + appBean.getAppsize());
        } else {
            tv_size.setText("大小:未知");
        }
        tv_type.setText(appBean.getTypename());
        tv_info.setText(appBean.getDescription());

        ImageView[] imgArray = new ImageView[]{img_1, img_2, img_3, img_4, img_5};

        List<GameBean.ImgBean> imgBean = bean.getImg();

        Picasso.with(GameActivity.this).load(ContextUtils.PATH + imgUrl).into(img_icon);
        for (int i = 0; i < imgBean.size(); i++) {
            Picasso
                    .with(GameActivity.this)
                    .load(ContextUtils.PATH + imgBean.get(i).getAddress())
                    .resize(500,700)
                    .into(imgArray[i]);
        }

        if (TextUtils.isEmpty(appBean.getDownload_addr())) {
            if (TextUtils.isEmpty(appBean.getVideo_addr())) {
                button.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            } else {
                downloadUrl = appBean.getVideo_addr();
            }
        } else {
            downloadUrl = appBean.getDownload_addr();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(downloadUrl);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        String gid = intent.getStringExtra("gameid");
        imgUrl = intent.getStringExtra("imgurl");
        OKHttpUtils.startOkhttp(handler, 0, ContextUtils.GAMEINFO + gid);

        ButterKnife.bind(this);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        img_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GameActivity.this, "分享功能正在开发中", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
