package com.module.login;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huang.citypicker.utils.StatusUtils;
import com.module.base.base.BaseActivity;
import com.module.base.base.BasePresenter;
import com.module.base.utils.KeyboardHelper;
import com.module.base.utils.VerifyCodeUtils;
import com.module.base.widgets.MyEditText;

/**
 * @author Huangshuang  2018/3/12 0012
 */

public class FindPassWordActivity extends BaseActivity implements VerifyCodeUtils.CountListener
        , MyEditText.OnTextWatcher {

    private Button btnCommit;
    private LinearLayout root;
    private TextView tvPhoneCode;
    private MyEditText editPhone, editPassWord, editPhoneCode;

    private String phone, passWord, phoneCode;

    private VerifyCodeUtils utils;
    private KeyboardHelper mKeyboardHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StatusUtils.setStatusBar(this, false, false);
        StatusUtils.setStatusTextColor(true, this);

        setContentView(R.layout.activity_find_password);

        titleBarBackground(R.color.defaultWhite)
                .leftImageRes(R.drawable.ic_arrow_left_black)
                .title("忘记密码")
                .titleColor(R.color.black);
    }

    @Override
    public void initView() {
        root = findViewById(R.id.root);
        btnCommit = findViewById(R.id.btn_commit);
        editPhone = findViewById(R.id.edit_phone);
        tvPhoneCode = findViewById(R.id.tv_phone_code);
        editPassWord = findViewById(R.id.edit_password);
        editPhoneCode = findViewById(R.id.edit_phone_code);

        showInPutKeybord(editPhone);
        utils = new VerifyCodeUtils(this, this);

        mKeyboardHelper = new KeyboardHelper(this, root);
        mKeyboardHelper.enable();
    }

    @Override
    public void setListener() {
        super.setListener();
        btnCommit.setOnClickListener(this);
        tvPhoneCode.setOnClickListener(this);

        editPhone.addOnTextWatcher(this);
        editPassWord.addOnTextWatcher(this);
        editPhoneCode.addOnTextWatcher(this);
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int i = v.getId();
        if (i == R.id.about_phone) {
        } else if (i == R.id.tv_phone_code) {
            utils.startCount();

        } else {
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
    public void count(Activity activity, String count) {

        tvPhoneCode.setText(count + "秒后重试");
        tvPhoneCode.setClickable(false);
        if ("0".equals(count)) {
            tvPhoneCode.setText("重新发送");
            tvPhoneCode.setClickable(true);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        utils.setStop(true);
        mKeyboardHelper.disable();
    }
}
