package com.bystanders.moeta.giftelves.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bystanders.moeta.giftelves.R;
import com.bystanders.moeta.giftelves.activity.GiftActivity;
import com.bystanders.moeta.giftelves.adapter.GiftAdapter;
import com.bystanders.moeta.giftelves.bean.GiftBean;
import com.bystanders.moeta.giftelves.utils.GsonUtils;
import com.bystanders.moeta.giftelves.utils.OKHttpUtils;
import com.bystanders.moeta.giftelves.view.GiftListHeadView;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class GiftFragment extends Fragment {
    GiftBean giftBean;
    GiftAdapter adapter;

    @BindView(R.id.gift_pull)
    PullToRefreshListView listView;
    int page = 1;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    initListView(msg);
                    break;
                case 1:
                    refreshListView(msg);
                    break;
                case 2:
                    updataListView(msg);
            }
        }
    };

    private void updataListView(Message msg) {
        String str = (String) msg.obj;
        Gson gson = GsonUtils.getGson();
        GiftBean giftBean_new = gson.fromJson(str, GiftBean.class);
        giftBean.getList().addAll(giftBean_new.getList());
        adapter.notifyDataSetChanged();

        listView.onRefreshComplete();
    }

    private void refreshListView(Message msg) {
        String str = (String) msg.obj;
        Gson gson = GsonUtils.getGson();
        GiftBean giftBean_new = gson.fromJson(str, GiftBean.class);
        giftBean.getList().clear();
        giftBean.getList().addAll(giftBean_new.getList());
        adapter.notifyDataSetChanged();

        listView.onRefreshComplete();
    }

    private void initListView(Message msg) {
        String str = (String) msg.obj;
        Gson gson = GsonUtils.getGson();
        giftBean = gson.fromJson(str, GiftBean.class);

        GiftListHeadView headView = new GiftListHeadView(getActivity());
        headView.setGiftBean(giftBean);
        headView.initView();

        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT);

        headView.setLayoutParams(layoutParams);
        ListView lv = listView.getRefreshableView();
        lv.addHeaderView(headView);

        adapter = new GiftAdapter(giftBean.getList(), getActivity());
        listView.setAdapter(adapter);
    }

    public GiftFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gift, container, false);
        ButterKnife.bind(this, view);
        OKHttpUtils.startOkhttp(handler, 0, "http://www.1688wan.com/majax.action?method=getGiftList&pageno=" + 0);

        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                OKHttpUtils.startOkhttp(handler, 1, "http://www.1688wan.com/majax.action?method=getGiftList&pageno=" + 1);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                OKHttpUtils.startOkhttp(handler, 2, "http://www.1688wan.com/majax.action?method=getGiftList&pageno=" + String.valueOf(++page));
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), GiftActivity.class);
                intent.putExtra("giftid", giftBean.getList().get(position-2).getId());
                intent.putExtra("giftname", giftBean.getList().get(position-2).getGiftname());
                getActivity().startActivity(intent);

            }
        });


        return view;
    }
}
