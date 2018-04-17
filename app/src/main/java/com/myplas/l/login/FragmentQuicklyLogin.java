package com.myplas.l.login;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.myplas.l.R;
import com.myplas.l.base.BaseFragment;
import com.myplas.l.common.utils.VerifyCodeUtils;
import com.myplas.l.common.widgets.MyEditText;

/**
 * @author Huangshuang  2018/3/12 0012
 */

public class FragmentQuicklyLogin extends BaseFragment implements View.OnClickListener,
        MyEditText.OnTextWatcher, VerifyCodeUtils.CountListener {
    private Button btnLogin;
    private ImageView ivNumber;
    private MyEditText editPhone, editNumber, editPhoneCode;

    private String phone, number, phoneCode;

    private VerifyCodeUtils utils;

    @Override
    public int getContentView() {
        return R.layout.fragment_quickly_login;
    }

    private TextView tvNewRegirter, tvPhoneCode;

    @Override
    public void initView() {
        btnLogin = f(R.id.btn_login);
        editPhone = f(R.id.edit_phone);
        ivNumber = f(R.id.iv_number_code);
        tvPhoneCode = f(R.id.tv_phone_code);
        tvNewRegirter = f(R.id.tv_register);
        editNumber = f(R.id.edit_number_code);
        editPhoneCode = f(R.id.edit_phone_code);

        utils = new VerifyCodeUtils(activity, this);
    }

    @Override
    public void setListener() {
        btnLogin.setOnClickListener(this);
        tvPhoneCode.setOnClickListener(this);
        tvNewRegirter.setOnClickListener(this);

        editPhone.addOnTextWatcher(this);
        editNumber.addOnTextWatcher(this);
        editPhoneCode.addOnTextWatcher(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:

                break;
            case R.id.tv_register:
                activity.startActivity(new Intent(activity, RegisterActivity.class));
                break;
            case R.id.tv_phone_code:
                utils.startCount();
                break;
            default:
                break;
        }
    }

    @Override
    public void onTextChanged(View v, String s) {
        changeBtnColor();
    }

    private void changeBtnColor() {
        if (isEmpty()) {
            btnLogin.setBackgroundResource(R.drawable.shape_login_blue);
        } else {
            btnLogin.setBackgroundResource(R.drawable.shape_login_blue_alpha);
        }
    }

    private boolean isEmpty() {
        phone = editPhone.getText().toString();
        number = editNumber.getText().toString();
        phoneCode = editPhoneCode.getText().toString();
        return !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(number) && !TextUtils.isEmpty(phoneCode);
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
    }
}
