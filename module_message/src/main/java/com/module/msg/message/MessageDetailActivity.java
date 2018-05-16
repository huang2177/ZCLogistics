package com.module.msg.message;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.module.base.base.BaseActivity;
import com.module.base.base.BasePresenter;
import com.module.base.base.Constant;
import com.module.msg.R;

/**
 * @author Huangshuang  2018/3/19 0019
 */

public class MessageDetailActivity extends BaseActivity {

    private RecyclerView recyclerView;

    private MessageDetailAdapter adapter;
    private int item;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);

        title(getIntent().getStringExtra(Constant.TITLE));
    }

    @Override
    public void initView() {
        recyclerView = findViewById(R.id.message_detail_rv);

        LinearLayoutManager manager = new LinearLayoutManager(this);

        item = Integer.parseInt(getIntent().getStringExtra(Constant.POSITION));
        adapter = new MessageDetailAdapter(this, item);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}
