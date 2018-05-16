package com.module.source;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.module.source.R;


/**
 * @author Huangshuang  2018/3/30 0030
 */

public class DriverRouteAdapter extends RecyclerView.Adapter {

    private Context context;

    public DriverRouteAdapter(Context context) {
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_driver_route, null);
        return new ViewHolder(view);
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
        return 4;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvStart, tvEnd, tvInfo;

        public ViewHolder(View view) {
            super(view);
            tvEnd = view.findViewById(R.id.item_route_end);
            tvInfo = view.findViewById(R.id.item_route_info);
            tvStart = view.findViewById(R.id.item_route_start);
        }
    }

}
