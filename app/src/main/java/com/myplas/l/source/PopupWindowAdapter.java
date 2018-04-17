package com.myplas.l.source;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myplas.l.R;
import com.myplas.l.common.utils.ToastUtil;

import java.util.List;

/**
 * @author Huangshuang  2018/3/30 0030
 */

public class PopupWindowAdapter extends BaseAdapter {

    public SparseArray<Boolean> clickFlags;
    private SparseArray<View> views;
    private int blue, white, littleBlack, gray;
    private SparseArray<TextView> tvArray, selectedArray;
    private Context context;
    private List<String> list;


    public PopupWindowAdapter(Context context, List<String> list) {
        this.list = list;
        this.context = context;

        views = new SparseArray<>();
        tvArray = new SparseArray<>();
        clickFlags = new SparseArray<>();
        selectedArray = new SparseArray<>();

        gray = ContextCompat.getColor(context, R.color.bgGray);
        blue = ContextCompat.getColor(context, R.color.mainColor);
        white = ContextCompat.getColor(context, R.color.defaultWhite);
        littleBlack = ContextCompat.getColor(context, R.color.littleBlack);
    }

    @Override
    public int getCount() {
        return list.size();
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
        if (views.get(position) == null) {
            convertView = View.inflate(context, R.layout.item_popupwindow, null);
            TextView textView = convertView.findViewById(R.id.item_pop_tv);
            textView.setText(list.get(position));

            tvArray.put(position, textView);
            views.put(position, convertView);
            clickFlags.put(position, false);

            if (position == 0) {
                tvArray.get(0).setTextColor(white);
                tvArray.get(0).setBackgroundColor(blue);
                selectedArray.put(-1, tvArray.get(0));
                clickFlags.put(position, true);
            }
        } else {
            convertView = views.get(position);
        }

        return convertView;
    }

    /**
     * 改变背景颜色
     *
     * @param position
     * @param radio    是否单选
     */
    public void selectItem(int position, boolean radio) {
        if (radio) {
            selectedArray.get(-1).setTextColor(littleBlack);
            selectedArray.get(-1).setBackgroundColor(gray);

            tvArray.get(position).setTextColor(white);
            tvArray.get(position).setBackgroundColor(blue);
            selectedArray.put(-1, tvArray.get(position));
        } else {
            if (clickFlags.get(position)) {
                clickFlags.put(position, false);
                tvArray.get(position).setBackgroundColor(gray);
                tvArray.get(position).setTextColor(littleBlack);
            } else {
                clickFlags.put(position, true);
                tvArray.get(position).setTextColor(white);
                tvArray.get(position).setBackgroundColor(blue);
            }
        }
    }
}



