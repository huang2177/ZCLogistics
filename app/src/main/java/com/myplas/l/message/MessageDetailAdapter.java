package com.myplas.l.message;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huangbryant.hindicator.HIndicatorAdapter;
import com.huangbryant.hindicator.HIndicatorBuilder;
import com.huangbryant.hindicator.HIndicatorDialog;
import com.huangbryant.hindicator.listener.OnItemClickListener;
import com.myplas.l.R;
import com.myplas.l.common.utils.ToastUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Huangshuang  2018/3/15 0015
 */

public class MessageDetailAdapter extends RecyclerView.Adapter implements OnItemClickListener {
    private int item;
    private Activity context;
    private int size = 10;
    private SparseArray<RecyclerView.ViewHolder> array;

    private HIndicatorDialog indicatorDialog;

    public MessageDetailAdapter(Activity context, int item) {
        this.item = item;
        this.context = context;
        array = new SparseArray<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {

        View view;
        RecyclerView.ViewHolder holder;

        if (item == 0 || item == 3) {
            view = LayoutInflater.from(context).inflate(R.layout.item_message_detail1, parent, false);
            holder = new ViewHolder1(view, position, item);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_message_detail2, parent, false);
            holder = new ViewHolder2(view, position, item);
        }

        array.put(position, holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //车辆预约通知 或者 成交通知
        if (item == 0 || item == 3) {
            ViewHolder1 viewHolder = (ViewHolder1) array.get(position);



        }
        //系统通知 或者 活动通知
        else {
            ViewHolder2 viewHolder = (ViewHolder2) array.get(position);



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


    @Override
    public void OnItemClick(int i) {

    }

    /**
     * 删除dialog
     *
     * @param viewLocate
     */
    private void showDialog(View viewLocate, int position, int item) {

        indicatorDialog = new HIndicatorBuilder(context)
                .width(230)
                .height(-1)
                .arrowDirection(HIndicatorBuilder.TOP)
                .bgColor(ContextCompat.getColor(context, R.color.black))
                .gravity(HIndicatorBuilder.GRAVITY_CENTER)
                .radius(16)
                .arrowWidth(30)
                .arrowRectage(0.5f)
                .dimAmount(0f)
                .clickListener(new MyOnClickListener(position, item))
                .data(Collections.singletonList("删除"))
                .create();
        indicatorDialog.show(viewLocate);
    }

    /**
     * 车辆预约通知 或者 成交通知
     */
    private class ViewHolder1 extends RecyclerView.ViewHolder {

        private LinearLayout layout;
        private TextView tvRoute, tvDetail, tvName, tvPrice, tvTime;

        public ViewHolder1(View itemView, final int position, int item) {
            super(itemView);

            layout = itemView.findViewById(R.id.msg_detail_ll);
            tvName = itemView.findViewById(R.id.msg_detail_name);
            tvTime = itemView.findViewById(R.id.msg_detail_time);
            tvPrice = itemView.findViewById(R.id.msg_detail_price);
            tvRoute = itemView.findViewById(R.id.msg_detail_route);
            tvDetail = itemView.findViewById(R.id.msg_detail_car_info);

            MyOnClickListener listener = new MyOnClickListener(position, item);

            layout.setOnClickListener(listener);

        }
    }

    /**
     * 系统通知 或者 活动通知
     */
    private class ViewHolder2 extends RecyclerView.ViewHolder {

        private View divider;

        private LinearLayout layout;
        private ImageView imageActivity;
        private TextView tvTitle, tvContent, tvTime;

        public ViewHolder2(View itemView, final int position, final int item) {
            super(itemView);

            layout = itemView.findViewById(R.id.msg_detail_ll);
            tvTime = itemView.findViewById(R.id.msg_detail_time);
            tvTitle = itemView.findViewById(R.id.msg_detail_title);
            divider = itemView.findViewById(R.id.msg_detail_divider);
            tvContent = itemView.findViewById(R.id.msg_detail_content);
            imageActivity = itemView.findViewById(R.id.msg_detail_img);

            if (item == 2) {
                divider.setVisibility(View.GONE);
                tvContent.setVisibility(View.GONE);
                imageActivity.setVisibility(View.VISIBLE);
            }

            MyOnClickListener listener = new MyOnClickListener(position, item);

            layout.setOnClickListener(listener);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    showDialog(tvTitle, position, item);

                    return false;
                }
            });
        }

    }

    private class MyOnClickListener implements View.OnClickListener, OnItemClickListener {

        private int position;
        /**
         * 外层列表的index
         */
        private int item;

        public MyOnClickListener(int position, int item) {
            this.item = item;
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            Log.e("-----onClick" + position, "---item" + item);
        }

        @Override
        public void OnItemClick(int i) {
            Log.e("-----OnItemClick" + position, "---item" + item);
        }
    }

}
