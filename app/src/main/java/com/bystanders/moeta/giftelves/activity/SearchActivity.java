package com.bystanders.moeta.giftelves.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bystanders.moeta.giftelves.R;
import com.bystanders.moeta.giftelves.adapter.GiftAdapter;
import com.bystanders.moeta.giftelves.bean.GiftBean;
import com.bystanders.moeta.giftelves.utils.ContextUtils;
import com.bystanders.moeta.giftelves.utils.GsonUtils;
import com.bystanders.moeta.giftelves.utils.OKHttpUtils;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity {

    GiftBean giftBean;
    GiftAdapter adapter;

    @BindView(R.id.search_back)
    ImageView img_back;
    @BindView(R.id.search_search)
    TextView tv_search;
    @BindView(R.id.search_edit)
    EditText edit_search;
    @BindView(R.id.search_list)
    ListView listView;
    String str;
    Gson gson;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    str = (String) msg.obj;
                    gson = GsonUtils.getGson();
                    giftBean = gson.fromJson(str, GiftBean.class);
                    adapter = new GiftAdapter(giftBean.getList(), SearchActivity.this);
                    listView.setAdapter(adapter);
                    break;
                case 1:
                    str = (String) msg.obj;
                    if (!TextUtils.isEmpty(str)){
                        gson = GsonUtils.getGson();
                        GiftBean giftBean_new = gson.fromJson(str, GiftBean.class);
                        giftBean.getList().clear();
                        giftBean.getList().addAll(giftBean_new.getList());
                        adapter.notifyDataSetChanged();
                    }

                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);
        OKHttpUtils.startOkhttp(handler, 0, ContextUtils.SEARCH);
    }

    @OnClick(R.id.search_search)
    void searchClick() {
        String searchStr = String.valueOf(edit_search.getText().toString()) ;
        OKHttpUtils.startPostOkhttp(handler, 1, ContextUtils.SEARCH,searchStr);
    }
}
