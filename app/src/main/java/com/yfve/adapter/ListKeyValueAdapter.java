package com.yfve.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.yfve.engineeringmode.R;
import com.yfve.tools.BeanTitleContent;
import java.util.List;

/* loaded from: classes1.dex */
public class ListKeyValueAdapter extends BaseAdapter {
    private Context mContext;
    private List<BeanTitleContent> mListsAhuPn;

    public ListKeyValueAdapter(Context context, List<BeanTitleContent> beanList) {
        this.mContext = null;
        this.mListsAhuPn = null;
        if (context == null || beanList == null) {
            return;
        }
        this.mContext = context;
        this.mListsAhuPn = beanList;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.mListsAhuPn.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int position) {
        return this.mListsAhuPn.get(position);
    }

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return position;
    }

    @Override // android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(this.mContext).inflate(R.layout.layout_listview_item_key_value, (ViewGroup) null);
            viewHolder.tvKey = (TextView) convertView.findViewById(R.id.item_two_tv_key);
            viewHolder.tvValue = (TextView) convertView.findViewById(R.id.item_two_tv_value);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvKey.setText(this.mListsAhuPn.get(position).getMstrTitle());
        viewHolder.tvValue.setText(this.mListsAhuPn.get(position).getMstrContent());
        return convertView;
    }

    /* loaded from: classes1.dex */
    class ViewHolder {
        TextView tvKey;
        TextView tvValue;

        ViewHolder() {
        }
    }
}
