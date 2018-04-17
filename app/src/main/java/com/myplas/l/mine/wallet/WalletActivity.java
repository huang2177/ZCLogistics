package com.myplas.l.mine.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.myplas.l.R;
import com.myplas.l.base.BaseActivity;
import com.myplas.l.base.BasePresenter;
import com.myplas.l.common.widgets.muiltlayout.CustomAdapter;
import com.myplas.l.common.widgets.muiltlayout.CustomProvider;

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
        switch (v.getId()) {
            case R.id.wallet_btn_commit:
                startActivity(new Intent(this, TakeOutMoneyActivity.class));
                break;
            case R.id.wallet_btn_recharge:

                break;
            default:
                break;
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
