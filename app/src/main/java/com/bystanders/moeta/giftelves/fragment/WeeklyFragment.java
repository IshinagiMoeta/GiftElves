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
import android.widget.ListView;

import com.bystanders.moeta.giftelves.R;
import com.bystanders.moeta.giftelves.activity.WeeklyActivity;
import com.bystanders.moeta.giftelves.adapter.WeeklyAdapter;
import com.bystanders.moeta.giftelves.bean.WeeklyBean;
import com.bystanders.moeta.giftelves.utils.GsonUtils;
import com.bystanders.moeta.giftelves.utils.OKHttpUtils;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeeklyFragment extends Fragment {

    @BindView(R.id.weekly_pull)
    PullToRefreshListView listView;
    WeeklyBean bean;
    WeeklyAdapter adapter;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    String str = (String) msg.obj;
                    Gson gson = GsonUtils.getGson();

                    bean = gson.fromJson(str,WeeklyBean.class);
                    adapter = new WeeklyAdapter(bean.getList(),getActivity());
                    listView.setAdapter(adapter);

                    break;
                case 1:
                    listView.onRefreshComplete();
                    break;
                case 2:
                    listView.onRefreshComplete();
                    break;
            }
        }
    };


    public WeeklyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weekly, container, false);
        ButterKnife.bind(this, view);
        OKHttpUtils.startOkhttp(handler, 0, "http://www.1688wan.com/majax.action?method=getWeekll&pageNo=" + 0);

        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                OKHttpUtils.startOkhttp(handler, 1, "http://www.1688wan.com/majax.action?method=getWeekll&pageNo=" + 1);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                OKHttpUtils.startOkhttp(handler, 2, "http://www.1688wan.com/majax.action?method=getWeekll&pageNo=" + 1);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), WeeklyActivity.class);
                intent.putExtra("name",bean.getList().get(position-1).getName());
                intent.putExtra("id",String.valueOf(bean.getList().get(position-1).getId()));
                intent.putExtra("iconurl",bean.getList().get(position-1).getIconurl());
                intent.putExtra("descs",bean.getList().get(position-1).getDescs());
                intent.putExtra("author",bean.getList().get(position-1).getAuthorimg());
                getActivity().startActivity(intent);
            }
        });


        return view;
    }

}
