package com.module.source;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.module.source.R;


/**
 * @author Huangshuang  2018/3/19 0019
 */

public class CargoOwnerAdapter extends RecyclerView.Adapter {

    private Context activity;
    private SparseArray<ViewHolder> array;

    public CargoOwnerAdapter(Context activity) {
        this.activity = activity;
        array = new SparseArray<>();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {

        View view = LayoutInflater.from(activity).inflate(R.layout.item_cargo_owner, parent, false);

        ViewHolder holder = new ViewHolder(view, position);
        array.put(position, holder);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvRoute, tvDetail, tvPrice;


        public ViewHolder(View itemView, int position) {
            super(itemView);
            tvRoute = itemView.findViewById(R.id.item_cargo_route);
            tvPrice = itemView.findViewById(R.id.item_cargo_price);
            tvDetail = itemView.findViewById(R.id.item_cargo_detail);

        }
    }
}
