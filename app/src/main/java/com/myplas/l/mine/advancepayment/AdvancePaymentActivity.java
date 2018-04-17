package com.myplas.l.mine.advancepayment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.myplas.l.R;
import com.myplas.l.base.BaseActivity;
import com.myplas.l.base.BasePresenter;

/**
 * @author Huangshuang  2018/3/22 0022
 */

public class AdvancePaymentActivity extends BaseActivity {

    private Button btnCommit;
    private AdvancePaymentDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_advancepayment);
        title("保证金认证");
    }

    @Override
    public void initView() {
        btnCommit = findViewById(R.id.btn_commit);
    }

    @Override
    public void setListener() {
        super.setListener();
        btnCommit.setOnClickListener(this);
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (dialog == null) {
            dialog = new AdvancePaymentDialog(this);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        } else {
            dialog.show();
        }
    }

    /**
     * 底部弹窗回调
     */
    public void onItemSelect(int position) {

    }
}
