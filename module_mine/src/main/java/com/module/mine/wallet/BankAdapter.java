package com.module.mine.wallet;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.module.mine.R;


/**
 * @author Huangshuang  2018/3/15 0015
 */

public class BankAdapter extends RecyclerView.Adapter {
    private Context context;
    private int size = 5;
    private SparseArray<ViewHolder> array;

    public BankAdapter(Context context) {
        this.context = context;
        array = new SparseArray<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bank, parent, false);
        ViewHolder holder = new ViewHolder(view);
        array.put(position, holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = array.get(position);
    }

    @Override
    public int getItemCount() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private RelativeLayout root;
        private TextView tvUnBind, tvCardNum;

        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.bank_root);
            tvUnBind = itemView.findViewById(R.id.bank_tv_unbind);
            tvCardNum = itemView.findViewById(R.id.bank_tv_card_num);

            tvUnBind.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //context.startActivity(new Intent(context, SourceDetailActivity.class));
        }
    }
}
