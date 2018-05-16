package com.module.source;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.Arrays;
import java.util.List;

/**
 * @author Huangshuang  2018/3/19 0019
 */

public class SourceDetailAdapter extends BaseAdapter {

    private Context context;
    private List<Integer> imgExpand, imgClose, imgTarget;
    private List<String> textExpand, textClose, textTarget;

    private boolean isExpanded;

    public SourceDetailAdapter(Context context) {
        initList();
        this.context = context;
        this.imgTarget = imgClose;
        this.textTarget = textClose;
    }

    @Override
    public int getCount() {
        return imgTarget.size();
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
        View convertView = View.inflate(context, R.layout.item_source_detail, null);
        TextView textView = convertView.findViewById(R.id.source_item_tv);
        View divider = convertView.findViewById(R.id.source_item_divider);
        ImageView imageView = convertView.findViewById(R.id.source_item_img);

        imageView.setOnClickListener(new MyOnClickListener(position));

        textView.setText(textTarget.get(position));
        textView.setCompoundDrawablesWithIntrinsicBounds(imgTarget.get(position), 0, 0, 0);

        if (position == 3 && isExpanded || position == 7 || position == 9) {
            divider.setVisibility(View.VISIBLE);
        }
        if (position == 6) {
            imageView.setImageResource(R.drawable.ic_see_route);
        }
        if (position == 11) {
            imageView.setImageResource(R.drawable.ic_btn_dial_blue);
        }
        return convertView;
    }

    /**
     * 初始化数据
     */
    private void initList() {
        imgExpand = Arrays.asList(R.drawable.ic_goodsname
                , R.drawable.ic_goodsvolume
                , R.drawable.ic_goodsweight
                , R.drawable.ic_goodsdate
                , R.drawable.ic_loadingaddress
                , R.drawable.ic_receivingaddress
                , R.drawable.ic_distance
                , R.drawable.ic_offer
                , R.drawable.ic_buslength
                , R.drawable.ic_bustype
                , R.drawable.ic_addressee
                , R.drawable.ic_contact);

        textExpand = Arrays.asList("货物名称："
                , "货物体积："
                , "货物重量："
                , "提货日起："
                , "装货地址："
                , "收货地址："
                , "路程："
                , "货主报价："
                , "需求车长："
                , "需求车型："
                , "收货人："
                , "联系方式：");

        imgClose = imgExpand.subList(0, 4);
        textClose = textExpand.subList(0, 4);
    }

    /**
     * 展开或者收起listView
     *
     * @param isExpanded
     */
    public void expandListView(boolean isExpanded) {
        this.isExpanded = isExpanded;
        this.imgTarget = isExpanded ? imgExpand : imgClose;
        this.textTarget = isExpanded ? textExpand : textClose;
        notifyDataSetChanged();
    }

    private class MyOnClickListener implements View.OnClickListener {
        int position;

        public MyOnClickListener(int positon) {
            this.position = positon;
        }

        @Override
        public void onClick(View v) {
            if (position == 6) {
                context.startActivity(new Intent(context, MapViewActivity.class));
            }
        }
    }
}
