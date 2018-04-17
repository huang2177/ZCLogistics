package com.myplas.l.source;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myplas.l.R;
import com.myplas.l.common.widgets.RoundImageView;

/**
 * @author Huangshuang  2018/3/19 0019
 */

public class SourceSupplyAdapter extends BaseAdapter {

    private Context context;

    public SourceSupplyAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 3;
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

        return initView(position);
    }

    /**
     * 初始化Item 并设置数据
     *
     * @param position
     */
    private View initView(int position) {
        View convertView = View.inflate(context, R.layout.item_source_supply, null);
        RoundImageView ivHead = convertView.findViewById(R.id.supply_iv);
        TextView tvName = convertView.findViewById(R.id.supply_tv_name);
        TextView tvTime = convertView.findViewById(R.id.supply_tv_time);
        TextView tvPrice = convertView.findViewById(R.id.supply_tv_price);
        TextView tvCarModel = convertView.findViewById(R.id.supply_car_model);

        if (position == 1) {
            ivHead.setTagEnable(true);
        }
        return convertView;
    }


    private class MyOnClickListener implements View.OnClickListener {
        int position;

        public MyOnClickListener(int positon) {
            this.position = positon;
        }

        @Override
        public void onClick(View v) {

        }
    }
}
