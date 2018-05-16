package com.module.mine.wallet;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.module.base.base.BaseActivity;
import com.module.base.base.BasePresenter;
import com.module.base.utils.VerifyCodeUtils;
import com.module.base.widgets.MyEditText;
import com.module.base.widgets.muiltlayout.MixedLayoutClickListener;
import com.module.mine.R;


/**
 * @author Huangshuang  2018/3/27 0027
 */

public class BindBankActivity extends BaseActivity implements
        VerifyCodeUtils.CountListener, MixedLayoutClickListener {

    private Button btnNext;
    private TextView tvPhoneCode;
    private LinearLayout layout1, layout2, layout3;
    private MyEditText editVerifyCode, editName, editCardCode, editCardModel, editPhone;


    /**
     * 标记当前是第几步
     */
    private int step;

    private VerifyCodeUtils utils;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_bank);

        title("绑定银行卡");
    }

    @Override
    public void initView() {

        btnNext = findViewById(R.id.bind_bank_btn);
        tvPhoneCode = findViewById(R.id.bind_bank_tv);
        editName = findViewById(R.id.bind_bank_edit_name);
        editPhone = findViewById(R.id.bind_bank_edit_phone);
        editCardCode = findViewById(R.id.bind_bank_edit_card);
        editVerifyCode = findViewById(R.id.bind_bank_edit_verify);
        editCardModel = findViewById(R.id.bind_bank_edit_card_model);

        layout1 = findViewById(R.id.bind_bank_ll1);
        layout2 = findViewById(R.id.bind_bank_ll2);
        layout3 = findViewById(R.id.bind_bank_ll3);

        utils = new VerifyCodeUtils(this, this);

        showInPutKeybord(editName);
    }


    @Override
    public void setListener() {
        super.setListener();

        btnNext.setOnClickListener(this);
        tvPhoneCode.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.bind_bank_tv) {
            utils.startCount();

        } else if (i == R.id.bind_bank_btn) {
            next();

        } else if (i == R.id.titleBar_left_img) {
            prevOrFinish();

        } else {
        }
    }

    /**
     * 点击 “<" (返回)时候相关操作
     */
    private void prevOrFinish() {

        if (step == 0) {
            finish();
        } else if (step == 2) {
            layout1.setVisibility(View.GONE);
            layout3.setVisibility(View.GONE);
            layout2.setVisibility(View.VISIBLE);

        } else {
            layout2.setVisibility(View.GONE);
            layout3.setVisibility(View.GONE);
            layout1.setVisibility(View.VISIBLE);
        }
        step--;
        btnNext.setText("下一步");
    }

    /**
     * 点击“下一步”时候相关操作
     */
    private void next() {
        step++;
        if (step == 2) {
            btnNext.setText("确定");
            layout2.setVisibility(View.GONE);
            layout1.setVisibility(View.GONE);
            layout3.setVisibility(View.VISIBLE);
        } else if (step < 2) {
            layout1.setVisibility(View.GONE);
            layout3.setVisibility(View.GONE);
            layout2.setVisibility(View.VISIBLE);
        } else {
            step--;
            //todo
        }
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }


    @Override
    public void count(Activity activity, String count) {
        tvPhoneCode.setText(count + "秒后重试");
        tvPhoneCode.setClickable(false);
        if ("0".equals(count)) {
            tvPhoneCode.setText("重新发送");
            tvPhoneCode.setClickable(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        utils.setStop(true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            prevOrFinish();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onTextChanged(int item, String s) {

    }
}
