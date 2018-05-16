package com.module.source;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.module.base.annotation.LoginInspect;
import com.module.base.widgets.RoundImageView;

/**
 * @author Huangshuang  2018/3/15 0015
 */

public class SourceAdapter extends RecyclerView.Adapter {
    private Context context;
    private int size = 15;
    private SparseArray<ViewHolder> array;

    public SourceAdapter(Context context) {
        this.context = context;
        array = new SparseArray<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_source, parent, false);
        ViewHolder holder = new ViewHolder(view);
        array.put(position, holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = array.get(position);
        if (position % 2 == 0) {
            viewHolder.ivHead.setTagEnable(true);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView ivDial;
        private RoundImageView ivHead;
        private TextView tvRoute, tvDetail, tvName, tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setTag(0);
            ivDial = itemView.findViewById(R.id.iv_dial);
            ivHead = itemView.findViewById(R.id.iv_head);
            tvName = itemView.findViewById(R.id.tv_name);
            tvRoute = itemView.findViewById(R.id.tv_route);
            tvDetail = itemView.findViewById(R.id.tv_detail);

            ivDial.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @LoginInspect(flag = "123")
        @Override
        public void onClick(View v) {
            if ((int) v.getTag() == 0) {
                context.startActivity(new Intent(context, SourceDetailActivity.class));
            } else {

            }
        }
    }
}
