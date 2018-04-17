package com.myplas.l.mine.wallet;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myplas.l.R;

import java.util.Arrays;
import java.util.List;

/**
 * @author Huangshuang  2018/3/19 0019
 */

public class WalletAdapter extends BaseAdapter {

    private Context context;

    public WalletAdapter(Context context) {
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

        return initView(position);
    }

    /**
     * 初始化Item 并设置数据
     *
     * @param position
     */
    private View initView(int position) {
        View convertView = null;
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_wallet, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    class ViewHolder {

        private TextView tvType, tvMoney, tvBalance, tvTime;

        public ViewHolder(View view) {
            tvType = view.findViewById(R.id.item_wallet_type);
            tvTime = view.findViewById(R.id.item_wallet_time);
            tvMoney = view.findViewById(R.id.item_wallet_money);
            tvBalance = view.findViewById(R.id.item_wallet_balance);
        }
    }
}
