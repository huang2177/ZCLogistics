package com.myplas.l.source;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myplas.l.R;
import com.myplas.l.base.BaseActivity;
import com.myplas.l.base.BasePresenter;
import com.myplas.l.common.utils.KeyboardHelper;
import com.myplas.l.common.widgets.MyEditText;

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
        switch (v.getId()) {
            case R.id.quote_btn:
                break;
            case R.id.quote_tv_car:
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //helper.disable();
    }
}
