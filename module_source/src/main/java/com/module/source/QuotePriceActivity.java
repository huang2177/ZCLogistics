package com.module.source;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.module.base.base.BaseActivity;
import com.module.base.base.BasePresenter;
import com.module.base.utils.KeyboardHelper;
import com.module.base.widgets.MyEditText;
import com.module.source.R;

/**
 * @author Huangshuang  2018/3/19 0019
 */

public class QuotePriceActivity extends BaseActivity {

    private Button btnComit;
    private LinearLayout root;
    private MyEditText editPrice;
    private TextView tvRoute, tvDetail, tvInfo, tvCar;

    private KeyboardHelper helper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_price);

        title("报价");
    }

    @Override
    public void initView() {
        root = findViewById(R.id.root);
        btnComit = findViewById(R.id.quote_btn);
        tvCar = findViewById(R.id.quote_tv_car);
        tvInfo = findViewById(R.id.quote_tv_info);
        tvRoute = findViewById(R.id.quote_tv_route);
        tvDetail = findViewById(R.id.quote_tv_detail);
        editPrice = findViewById(R.id.quote_edit_price);

        helper = new KeyboardHelper(this, root);
        // helper.enable();

    }

    @Override
    public void setListener() {
        super.setListener();
        tvCar.setOnClickListener(this);
        btnComit.setOnClickListener(this);
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int i = v.getId();
        if (i == R.id.quote_btn) {
        } else if (i == R.id.quote_tv_car) {
        } else {
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //helper.disable();
    }
}
