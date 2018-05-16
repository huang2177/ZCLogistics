package com.module.mine.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.module.base.base.BaseActivity;
import com.module.base.base.BasePresenter;
import com.module.mine.R;

/**
 * @author Huangshuang  2018/3/27 0027
 */

public class BankActivity extends BaseActivity {

    private RecyclerView recyclerView;

    private BankAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);
        title("银行卡").rightImageRes(R.drawable.ic_add);
    }

    @Override
    public void initView() {
        recyclerView = findViewById(R.id.bank_rv);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        adapter = new BankAdapter(this);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onRightClick() {
        startActivity(new Intent(this, BindBankActivity.class));
    }
}
