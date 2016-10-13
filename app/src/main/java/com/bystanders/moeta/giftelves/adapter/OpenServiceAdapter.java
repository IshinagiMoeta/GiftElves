package com.bystanders.moeta.giftelves.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bystanders.moeta.giftelves.R;
import com.bystanders.moeta.giftelves.activity.GameActivity;
import com.bystanders.moeta.giftelves.bean.OpenServiceBean;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.TreeSet;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ishinagi_moeta on 2016/10/8.
 */
public class OpenServiceAdapter implements ExpandableListAdapter {
    List<OpenServiceBean> list;
    TreeSet<String> data;
    Context context;

    public OpenServiceAdapter(List<OpenServiceBean> list, TreeSet<String> data, Context context) {
        this.list = list;
        this.data = data;
        this.context = context;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getInfo().size();
    }

    @Override
    public String getGroup(int groupPosition) {
        int i = 0;
        for (String string : data) {
            if (i == groupPosition) {
                return string;
            }
            i++;
        }
        return null;
    }

    @Override
    public OpenServiceBean.InfoBean getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getInfo().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        TextView view = new TextView(context);
        view.setBackgroundResource(R.color.colorPrimaryDark);
        view.setPadding(5, 2, 5, 2);
        if (groupPosition==0) {
            view.setText(getGroup(groupPosition) + "(今日开服)");
        } else {
            view.setText(getGroup(groupPosition));
        }
        convertView = view;
        convertView.setFocusable(false);
        convertView.setFocusableInTouchMode(false);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.open_service_list_item, null);
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final OpenServiceBean.InfoBean child = getChild(groupPosition, childPosition);
        viewHolder.tv_title.setText(child.getGname());
        viewHolder.tv_area.setText(child.getArea());
        String str = child.getStarttime().substring(5, child.getStarttime().length());
        viewHolder.tv_time.setText(str);
        viewHolder.tv_operators.setText("运营商:"+child.getOperators());
        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GameActivity.class);
                intent.putExtra("gameid", child.getGid());
                intent.putExtra("imgurl",child.getIconurl());
                context.startActivity(intent);
            }
        });
        Picasso.with(context).load("http://www.1688wan.com/" + child.getIconurl()).into(viewHolder.imageView);

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.open_service_list_title)
        TextView tv_title;
        @BindView(R.id.open_service_list_time)
        TextView tv_time;
        @BindView(R.id.open_service_list_operators)
        TextView tv_operators;
        @BindView(R.id.open_service_list_area)
        TextView tv_area;
        @BindView(R.id.open_service_list_image)
        ImageView imageView;
        @BindView(R.id.open_service_list_button)
        Button button;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }
}
