package com.myplas.l.mine.wallet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.myplas.l.R;
import com.myplas.l.base.BaseActivity;
import com.myplas.l.base.BasePresenter;
import com.myplas.l.common.widgets.muiltlayout.CustomAdapter;
import com.myplas.l.common.widgets.muiltlayout.CustomProvider;

import java.util.Arrays;

/**
 * @author Huangshuang  2018/3/29 0029
 */

public class TradeDetailActivity extends BaseActivity {

    private ListView listView;
    private CustomProvider provider;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assets);

        title("交易详情");
    }

    @Override
    public void initView() {
        listView = findViewById(R.id.assets_lv);

        provider = new CustomProvider(this)
                .leftTexts(Arrays.asList("交易号", "交易类型", "交易金额", "交易时间", "交易方式", "交易状态"))
                .rightTexts(Arrays.asList("165468134875465", "-5000.00", "提现", "2017-02-18", "线上", "提交申请中"));
        listView.setAdapter(new CustomAdapter(provider));
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }
}
