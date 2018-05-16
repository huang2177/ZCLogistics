package com.module.mine.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.module.base.base.BaseActivity;
import com.module.base.base.BasePresenter;
import com.module.base.widgets.muiltlayout.CustomAdapter;
import com.module.base.widgets.muiltlayout.CustomProvider;
import com.module.mine.R;

import java.util.Arrays;

/**
 * @author Huangshuang  2018/3/20 0020
 */

public class MyAssetsActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assets);

        title("我的钱包");
    }

    @Override
    public void initView() {

        listView = findViewById(R.id.assets_lv);

        CustomProvider provider = new CustomProvider(this)
                .rightTexts(Arrays.asList("1000", ""))
                .leftTexts(Arrays.asList("钱包", "银行卡"))
                .leftImgs(Arrays.asList(R.drawable.ic_wallet1, R.drawable.ic_bank_card))
                .rightImgs(Arrays.asList(R.drawable.ic_arrow_right_gray, R.drawable.ic_arrow_right_gray));

        adapter = new CustomAdapter(provider);
        listView.setAdapter(adapter);
    }

    @Override
    public void setListener() {
        super.setListener();
        listView.setOnItemClickListener(this);
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            startActivity(new Intent(this, WalletActivity.class));
        } else {
            startActivity(new Intent(this, BankActivity.class));
        }
    }
}



