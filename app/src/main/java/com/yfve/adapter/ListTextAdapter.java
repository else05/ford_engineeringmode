package com.yfve.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.yfve.engineeringmode.R;

/* loaded from: classes1.dex */
public class ListTextAdapter extends BaseAdapter {
    private Context mContext;
    private String[] mstrsListEntry;

    public ListTextAdapter(Context context, String[] stringList) {
        this.mstrsListEntry = new String[0];
        this.mContext = null;
        if (context == null || stringList == null) {
            return;
        }
        this.mContext = context;
        this.mstrsListEntry = stringList;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.mstrsListEntry.length;
    }

    @Override // android.widget.Adapter
    public Object getItem(int position) {
        return this.mstrsListEntry[position];
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
            convertView = LayoutInflater.from(this.mContext).inflate(R.layout.layout_listview_item_listtext, (ViewGroup) null);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.item_one_tv_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String strEntry = this.mstrsListEntry[position];
        viewHolder.textView.setText(strEntry);
        return convertView;
    }

    /* loaded from: classes1.dex */
    class ViewHolder {
        TextView textView;

        ViewHolder() {
        }
    }
}
