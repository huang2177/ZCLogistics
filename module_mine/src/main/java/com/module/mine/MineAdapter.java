package com.module.mine;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.Arrays;
import java.util.List;

/**
 * @author Huangshuang  2018/3/19 0019
 */

public class MineAdapter extends BaseAdapter {

    private Context context;
    private List<Integer> img;
    private List<String> text;

    public MineAdapter(Context context) {
        this.context = context;
        initList();
    }

    @Override
    public int getCount() {
        return img.size();
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
        View convertView = View.inflate(context, R.layout.item_mine, null);

        TextView textView = convertView.findViewById(R.id.item_mine_tv);
        textView.setText(text.get(position));
        textView.setCompoundDrawablesWithIntrinsicBounds(img.get(position)
                , 0
                , R.drawable.ic_arrow_right_gray
                , 0);

        return convertView;
    }


    /**
     * 初始化数据
     */
    private void initList() {
        img = Arrays.asList(R.drawable.ic_mine_carrier
                , R.drawable.ic_mine_mywallet
                , R.drawable.ic_mine_carmanager
                , R.drawable.ic_mine_commonline
                , R.drawable.ic_mine_onlineservice
                , R.drawable.ic_mine_setup);

        text = Arrays.asList("承运单"
                , "我的钱包"
                , "车辆管理"
                , "我的常用路线"
                , "在线客服"
                , "设置");

    }
}
