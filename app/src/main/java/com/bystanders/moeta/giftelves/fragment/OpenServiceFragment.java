package com.bystanders.moeta.giftelves.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.bystanders.moeta.giftelves.R;
import com.bystanders.moeta.giftelves.adapter.OpenServiceAdapter;
import com.bystanders.moeta.giftelves.bean.OpenServiceBean;
import com.bystanders.moeta.giftelves.utils.GsonUtils;
import com.bystanders.moeta.giftelves.utils.OKHttpUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class OpenServiceFragment extends Fragment {

    OpenServiceBean serviceBean;
    List<OpenServiceBean> list;
    TreeSet<String> data = new TreeSet<>();
    @BindView(R.id.open_service_listview)
    ExpandableListView listView;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    initListview(msg);
                    break;
            }
        }
    };

    public OpenServiceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_open, container, false);
        ButterKnife.bind(this, view);
        OKHttpUtils.startOkhttp(handler, 0, "http://www.1688wan.com/majax.action?method=getJtkaifu");
        return view;
    }

    private void initListview(Message msg) {
        String str = (String) msg.obj;
        Gson gson = GsonUtils.getGson();
        serviceBean = gson.fromJson(str, OpenServiceBean.class);

        List<OpenServiceBean.InfoBean> info = serviceBean.getInfo();
        for (int i = 0; i < info.size(); i++) {
            data.add(info.get(i).getAddtime());
        }
        list = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            int pos = 0;
            for (String string : data) {
                if (pos == i) {
                    OpenServiceBean newBean = new OpenServiceBean();
                    List<OpenServiceBean.InfoBean> newInfo = new ArrayList<>();
                    for (int j = 0; j < info.size(); j++) {
                        if (info.get(j).getAddtime().equals(string)) {
                            newInfo.add(info.get(j));
                        }
                    }
                    newBean.setInfo(newInfo);
                    list.add(newBean);
                }
                pos++;
            }
        }

        OpenServiceAdapter adapter = new OpenServiceAdapter(list, data, getActivity());
        listView.setAdapter(adapter);
        for (int i = 0; i < adapter.getGroupCount(); i++) {
            listView.expandGroup(i);
        }
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
    }

}
