package com.module.mine.setting;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.module.base.base.BaseActivity;
import com.module.base.base.BasePresenter;
import com.module.base.utils.VerifyCodeUtils;
import com.module.base.widgets.MyEditText;
import com.module.mine.R;

/**
 * @author Huangshuang  2018/3/20 0020
 */

public class ModifyActivity extends BaseActivity implements
        VerifyCodeUtils.CountListener,
        MyEditText.OnTextWatcher {

    private Button btnCommit;
    private LinearLayout root;
    private TextView tvPhoneCode;
    private MyEditText editPhone, editPassWord, editPhoneCode;

    private String phone, passWord, phoneCode;

    private VerifyCodeUtils utils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        title("修改密码");
    }

    @Override
    public void initView() {

        root = findViewById(R.id.modify_root);
        btnCommit = findViewById(R.id.modify_btn);
        editPhone = findViewById(R.id.modify_phone);
        editPhoneCode = findViewById(R.id.modify_phone_code);
        tvPhoneCode = findViewById(R.id.modify_tv_phone_code);
        editPassWord = findViewById(R.id.modify_new_password);

        utils = new VerifyCodeUtils(this, this);

    }

    @Override
    public void setListener() {
        super.setListener();
        btnCommit.setOnClickListener(this);
        tvPhoneCode.setOnClickListener(this);
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        int i = v.getId();
        if (i == R.id.modify_btn) {
        } else if (i == R.id.modify_tv_phone_code) {
            utils.startCount();

        } else {
        }
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
    public void onTextChanged(View v, String s) {
        changeBtnColor();
    }

    private void changeBtnColor() {
        if (isEmpty()) {
            btnCommit.setBackgroundResource(R.drawable.shape_login_blue);
        } else {
            btnCommit.setBackgroundResource(R.drawable.shape_login_blue_alpha);
        }
    }

    private boolean isEmpty() {
        phone = editPhone.getText().toString();
        passWord = editPassWord.getText().toString();
        phoneCode = editPhoneCode.getText().toString();
        return !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(passWord) && !TextUtils.isEmpty(phoneCode);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        utils.setStop(true);
    }
}



