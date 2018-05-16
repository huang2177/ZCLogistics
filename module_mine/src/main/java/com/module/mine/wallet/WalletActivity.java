package com.module.mine.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.module.base.base.BaseActivity;
import com.module.base.base.BasePresenter;
import com.module.mine.R;

/**
 * @author Huangshuang  2018/3/27 0027
 */

public class WalletActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private TextView tvMoney;
    private ListView listView;
    private Button btnCommit, btnReCharge;

    private WalletAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        title("钱包");
    }

    @Override
    public void initView() {
        listView = findViewById(R.id.wallet_lv);
        tvMoney = findViewById(R.id.wallet_money);
        btnCommit = findViewById(R.id.wallet_btn_commit);
        btnReCharge = findViewById(R.id.wallet_btn_recharge);

        adapter = new WalletAdapter(this);
        listView.setAdapter(adapter);
    }

    @Override
    public void setListener() {
        super.setListener();
        btnCommit.setOnClickListener(this);
        btnReCharge.setOnClickListener(this);
        listView.setOnItemClickListener(this);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        int i = v.getId();
        if (i == R.id.wallet_btn_commit) {
            startActivity(new Intent(this, TakeOutMoneyActivity.class));

        } else if (i == R.id.wallet_btn_recharge) {
        } else {
        }
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(this, TradeDetailActivity.class));
    }
}
