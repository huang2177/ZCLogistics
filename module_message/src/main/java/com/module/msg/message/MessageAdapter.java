package com.module.msg.message;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.module.msg.R;


/**
 * @author Huangshuang  2018/3/19 0019
 */

public class MessageAdapter extends BaseAdapter {

    private Context context;

    public MessageAdapter(Context context) {
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

        return initView(position);
    }

    /**
     * 初始化Item 并设置数据
     *
     * @param position
     */
    private View initView(int position) {
        View convertView = View.inflate(context, R.layout.item_message, null);
        View divider = convertView.findViewById(R.id.msg_divider);
        TextView tvDot = convertView.findViewById(R.id.msg_tv_dot);
        TextView tvType = convertView.findViewById(R.id.msg_tv_type);
        TextView tvTime = convertView.findViewById(R.id.msg_tv_time);
        ImageView ivType = convertView.findViewById(R.id.msg_iv_type);
        TextView tvDetail = convertView.findViewById(R.id.msg_tv_detail);

        switch (position) {
            case 0:
                ivType.setImageResource(R.drawable.img_msg_appointment);
                break;
            case 1:
                tvType.setText("系统通知");
                ivType.setImageResource(R.drawable.img_msg_system);
                break;
            case 2:
                tvType.setText("活动通知");
                ivType.setImageResource(R.drawable.img_msg_activity);
                break;
            case 3:
                tvType.setText("成交通知");
                divider.setVisibility(View.GONE);
                ivType.setImageResource(R.drawable.img_msg_deal);
                break;
            default:
                break;
        }
        return convertView;
    }

}
