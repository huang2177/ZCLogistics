package com.myplas.l.mine.advancepayment;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.myplas.l.R;
import com.myplas.l.common.widgets.MyBottomDialog;

/**
 * @author Huangshuang  2018/3/22 0022
 */

public class AdvancePaymentDialog extends MyBottomDialog {

    private AdvancePaymentActivity activity;
    private TextView tvClose, tvWechatPay, tvAliPay, tvWalletPay;

    private Drawable checked;
    private int aliImg, wechatImg, walletImg, icUnchecked;


    public AdvancePaymentDialog(@NonNull Activity activity) {
        super(activity);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_advancepayment;
    }

    @Override
    protected void initView() {

        activity = (AdvancePaymentActivity) super.activity;

        tvAliPay = findViewById(R.id.dialog_advancepayment_ali);
        tvClose = findViewById(R.id.dialog_advancepayment_close);
        tvWechatPay = findViewById(R.id.dialog_advancepayment_wechat);
        tvWalletPay = findViewById(R.id.dialog_advancepayment_wallet);

        aliImg = R.drawable.ic_zhifubao;
        wechatImg = R.drawable.ic_weixin;
        walletImg = R.drawable.ic_wallet;
        icUnchecked = R.drawable.ic_uncheck;

        checked = tvAliPay.getCompoundDrawables()[2];
    }

    @Override
    protected void setListener() {
        tvClose.setOnClickListener(this);
        tvAliPay.setOnClickListener(this);
        tvWechatPay.setOnClickListener(this);
        tvWalletPay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_advancepayment_close:
                dismiss();
                break;
            case R.id.dialog_advancepayment_ali:
                select(tvAliPay);
                activity.onItemSelect(1);
                break;
            case R.id.dialog_advancepayment_wechat:
                select(tvWechatPay);
                activity.onItemSelect(2);
                break;
            case R.id.dialog_advancepayment_wallet:
                select(tvWalletPay);
                activity.onItemSelect(3);
                break;
            default:
                break;
        }
    }


    private void select(TextView target) {

        tvAliPay.setCompoundDrawablesWithIntrinsicBounds(aliImg, 0, icUnchecked, 0);
        tvWalletPay.setCompoundDrawablesWithIntrinsicBounds(walletImg, 0, icUnchecked, 0);
        tvWechatPay.setCompoundDrawablesWithIntrinsicBounds(wechatImg, 0, icUnchecked, 0);

        target.setCompoundDrawables(target.getCompoundDrawables()[0]
                , null
                , checked
                , null);
    }

}
