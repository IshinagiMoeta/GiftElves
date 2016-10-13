package com.bystanders.moeta.giftelves.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;

import com.bystanders.moeta.giftelves.R;
import com.bystanders.moeta.giftelves.activity.GameActivity;
import com.bystanders.moeta.giftelves.adapter.HotGridAdapter;
import com.bystanders.moeta.giftelves.adapter.HotListAdapter;
import com.bystanders.moeta.giftelves.bean.HotBean;
import com.bystanders.moeta.giftelves.utils.GsonUtils;
import com.bystanders.moeta.giftelves.utils.OKHttpUtils;
import com.bystanders.moeta.giftelves.view.CustomListView;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 */
public class HotFragment extends Fragment {

    @BindView(R.id.hot_list)
    CustomListView listView;
    @BindView(R.id.hot_grid)
    GridView gridView;

    HotListAdapter listAdapter;
    HotGridAdapter gridAdapter;

    HotBean bean;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    initList(msg);
                    break;
            }
        }
    };

    private void initList(Message msg) {
        String str = (String) msg.obj;
        Gson gson = GsonUtils.getGson();
        bean = gson.fromJson(str, HotBean.class);

        final List<HotBean.InfoBean.Push1Bean> push1 = bean.getInfo().getPush1();
        final List<HotBean.InfoBean.Push2Bean> push2 = bean.getInfo().getPush2();

        listAdapter = new HotListAdapter(push1, getActivity());
        gridAdapter = new HotGridAdapter(push2, getActivity());

        listView.setAdapter(listAdapter);
        gridView.setAdapter(gridAdapter);

        listView.setListViewHeightBasedOnChildren(listView);
        setListViewHeightBasedOnChildren(gridView);

        listAdapter.notifyDataSetChanged();
        gridAdapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), GameActivity.class);
                intent.putExtra("gameid", push1.get(position).getAppid());
                intent.putExtra("imgurl", push1.get(position).getLogo());
                getActivity().startActivity(intent);
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), GameActivity.class);
                intent.putExtra("gameid", push2.get(position).getAppid());
                intent.putExtra("imgurl", push2.get(position).getLogo());
                getActivity().startActivity(intent);
            }
        });
    }


    public HotFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hot, container, false);
        ButterKnife.bind(this, view);
        OKHttpUtils.startOkhttp(handler, 0, "http://www.1688wan.com//majax.action?method=hotpushForAndroid");
        return view;
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
