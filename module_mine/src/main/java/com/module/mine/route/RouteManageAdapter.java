package com.module.mine.route;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.module.mine.R;


/**
 * @author Huangshuang  2018/3/30 0030
 */

public class RouteManageAdapter extends BaseAdapter {

    private Context context;

    public RouteManageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_route_manager, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    class ViewHolder {
        TextView tvStart, tvEnd, tvInfo;

        public ViewHolder(View view) {
            tvEnd = view.findViewById(R.id.item_route_end);
            tvInfo = view.findViewById(R.id.item_route_info);
            tvStart = view.findViewById(R.id.item_route_start);
        }
    }

}
