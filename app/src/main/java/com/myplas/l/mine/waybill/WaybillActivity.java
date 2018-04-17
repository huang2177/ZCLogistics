package com.myplas.l.mine.waybill;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.myplas.l.R;
import com.myplas.l.base.BaseActivity;
import com.myplas.l.base.BasePresenter;
import com.myplas.l.common.widgets.XPopupWindow;
import com.myplas.l.source.PopupWindowAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * @author Huangshuang  2018/3/20 0020
 */

public class WaybillActivity extends BaseActivity implements XPopupWindow.OnItemClickListener {

    private View dialogView;
    private RecyclerView mRecyclerView;
    private XPopupWindow popupWindow;
    private List<String> mList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waybill);

        title("承运单").rightText("全部");
    }

    @Override
    public void initView() {
        mRecyclerView = findViewById(R.id.waybill_rv);
        dialogView = View.inflate(this, R.layout.dialog_popupwindow, null);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);

        mRecyclerView.setAdapter(new WaybillAdapter(this));
    }


    @Override
    public BasePresenter createPresenter() {
        return null;
    }


    /**
     * @param type     点击item 或者 textView
     * @param position 对应的item的位置
     */
    public void onClick(int type, int position) {
        switch (type) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }

        startActivity(new Intent(this, WayBillDetailActivity.class));

    }

    @Override
    public void onRightClick() {
        if (popupWindow == null) {
            mList = Arrays.asList("全部"
                    , "待取货"
                    , "待接单"
                    , "待确认"
                    , "运送中"
                    , "待支付"
                    , "已完成"
                    , "已取消");
            popupWindow = new XPopupWindow(dialogView, this)
                    .setType(1)
                    .title("选择承运单状态")
                    .setList(mList)
                    .setListener(this);
            popupWindow.showAsDropDown(titleBarContainer);
        } else {
            popupWindow.showAsDropDown(titleBarContainer);
        }
    }

    @Override
    public void onPopItemClick(int type, int position) {
        if (position != -1) {
            rightText(mList.get(position));
        }
    }
}



