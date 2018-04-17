package com.myplas.l.mine.waybill;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myplas.l.R;
import com.myplas.l.common.utils.ScreenUtils;
import com.myplas.l.source.SourceDetailActivity;

import java.util.Arrays;
import java.util.List;

/**
 * @author Huangshuang  2018/3/19 0019
 */

public class WayBillDetailAdapter extends BaseAdapter {

    private Context context;
    private List<Integer> img;
    private List<String> text;
    private SparseArray<TextView> array;

    private boolean isExpanded;

    public WayBillDetailAdapter(Context context) {
        initList();
        this.context = context;
        array = new SparseArray<>();
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
        View convertView = View.inflate(context, R.layout.item_waybill_detail, null);
        TextView textView = convertView.findViewById(R.id.source_item_tv);
        View divider = convertView.findViewById(R.id.source_item_divider);
        ImageView imageView = convertView.findViewById(R.id.source_item_img);

        imageView.setOnClickListener(new MyOnClickListener(position));

        array.put(position, textView);
        textView.setText(text.get(position));
        textView.setCompoundDrawablesWithIntrinsicBounds(img.get(position), 0, 0, 0);


        switch (position) {
            case 0:
                divider.setVisibility(View.VISIBLE);
                imageView.setImageResource(R.drawable.ic_arrow_right_gray);
                array.get(position).setLayoutParams(getLayoutParams(array.get(position)));
                convertView.setOnClickListener(new MyOnClickListener(position));
                break;
            case 1:
                divider.setVisibility(View.VISIBLE);
                array.get(position).setLayoutParams(getLayoutParams(array.get(position)));
                break;
            case 4:
                imageView.setImageResource(R.drawable.ic_see_route);
                imageView.setOnClickListener(new MyOnClickListener(position));
                break;
            case 6:
                divider.setVisibility(View.VISIBLE);
                break;
            case 8:
                divider.setVisibility(View.VISIBLE);
                imageView.setImageResource(R.drawable.ic_btn_dial_blue);
                imageView.setOnClickListener(new MyOnClickListener(position));
                break;
            default:
                break;
        }
        return convertView;
    }

    private LinearLayout.LayoutParams getLayoutParams(TextView textView) {
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) textView.getLayoutParams();
        lp.topMargin = ScreenUtils.dp2px(context, 10);
        lp.bottomMargin = ScreenUtils.dp2px(context, 10);
        lp.weight = ScreenUtils.getScreenWidth(context);

        return lp;
    }

    /**
     * 初始化数据
     */
    private void initList() {

        img = Arrays.asList(R.drawable.ic_goodsname
                , R.drawable.ic_state
                , R.drawable.ic_loadingaddress
                , R.drawable.ic_receivingaddress
                , R.drawable.ic_distance
                , R.drawable.ic_offer
                , R.drawable.ic_bustype
                , R.drawable.ic_addressee
                , R.drawable.ic_contact);

        text = Arrays.asList("塑料瓶子  50吨"
                , "状态：待确认"
                , "装货地址："
                , "收货地址："
                , "路程："
                , "货主报价："
                , "成交运费："
                , "收货人："
                , "联系方式：");

    }


    private class MyOnClickListener implements View.OnClickListener {
        int position;

        public MyOnClickListener(int positon) {
            this.position = positon;
        }

        @Override
        public void onClick(View v) {
            if (position == 0) {
                context.startActivity(new Intent(context, SourceDetailActivity.class));
            }
        }
    }
}
