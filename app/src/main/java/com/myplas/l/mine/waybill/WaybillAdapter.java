package com.myplas.l.mine.waybill;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.myplas.l.R;

/**
 * @author Huangshuang  2018/3/19 0019
 */

public class WaybillAdapter extends RecyclerView.Adapter {

    private WaybillActivity activity;
    private SparseArray<ViewHolder> array;

    public WaybillAdapter(WaybillActivity activity) {
        this.activity = activity;
        array = new SparseArray<>();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {

        View view = LayoutInflater.from(activity).inflate(R.layout.item_waybill, parent, false);

        ViewHolder holder = new ViewHolder(view, position);
        array.put(position, holder);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivTag;
        private TextView tvRoute, tvDetail, tvType1, tvType2;


        public ViewHolder(View itemView, int position) {
            super(itemView);
            ivTag = itemView.findViewById(R.id.waybill_iv_tag);
            tvType1 = itemView.findViewById(R.id.waybill_tv_type1);
            tvType2 = itemView.findViewById(R.id.waybill_tv_type2);
            tvRoute = itemView.findViewById(R.id.waybill_tv_route);
            tvDetail = itemView.findViewById(R.id.waybill_tv_detail);

            MyOnClickListener listener = new MyOnClickListener(position);

            itemView.setTag(0);
            itemView.setOnClickListener(listener);

            tvType1.setTag(1);
            tvType1.setOnClickListener(listener);

            tvType2.setTag(2);
            tvType2.setOnClickListener(listener);

        }
    }


    public class MyOnClickListener implements View.OnClickListener {
        private int position;

        public MyOnClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            activity.onClick((int) v.getTag(), position);
        }
    }
}
