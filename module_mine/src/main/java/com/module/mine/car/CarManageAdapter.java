package com.module.mine.car;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.module.mine.R;


/**
 * @author Huangshuang  2018/3/30 0030
 */

public class CarManageAdapter extends BaseAdapter {

    private Context context;

    public CarManageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 10;
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
            convertView = View.inflate(context, R.layout.item_car_manager, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    class ViewHolder {
        TextView tvRoute, tvCarNum, tvCarLength, tvCarModel, tvCarTime, tvCarWeight;

        public ViewHolder(View view) {
            tvCarNum = view.findViewById(R.id.item_car_num);
            tvRoute = view.findViewById(R.id.item_car_route);
            tvCarTime = view.findViewById(R.id.item_car_time);
            tvCarModel = view.findViewById(R.id.item_car_model);
            tvCarWeight = view.findViewById(R.id.item_car_weight);
            tvCarLength = view.findViewById(R.id.item_car_length);
        }
    }

}
