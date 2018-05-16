package com.module.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.alibaba.android.arouter.launcher.ARouter;
import com.module.base.base.BaseFragment;
import com.module.base.base.Constant;
import com.module.base.utils.SPUtil;
import com.module.base.widgets.MyEditText;

/**
 * @author Huangshuang  2018/3/12 0012
 */

public class FragmentCommonLogin extends BaseFragment implements View.OnClickListener,
        MyEditText.OnTextWatcher {
    private Button btnLogin;
    private MyEditText editPhone, editPassword;
    private TextView tvNewRegirter, tvFindPassWord;
    private String phone, passWord;


    @Override
    public int getContentView() {
        return R.layout.fragment_common_login;
    }

    @Override
    public void initView() {
        btnLogin = f(R.id.btn_login);
        editPhone = f(R.id.edit_phone);
        editPassword = f(R.id.edit_password);
        tvNewRegirter = f(R.id.tv_register);
        tvFindPassWord = f(R.id.tv_forget);

        showInPutKeybord(editPhone);
    }

    @Override
    public void setListener() {
        btnLogin.setOnClickListener(this);
        tvNewRegirter.setOnClickListener(this);
        tvFindPassWord.setOnClickListener(this);

        editPhone.addOnTextWatcher(this);
        editPassword.addOnTextWatcher(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_login) {
            SPUtil.getInstance(activity).setBooloean(Constant.ISLOGINED, true);
            ARouter.getInstance().build(Constant.PATH_MAINACTIVITY).navigation();
            activity.finish();

        } else if (i == R.id.tv_register) {
            activity.startActivity(new Intent(activity, RegisterActivity.class));

        } else if (i == R.id.tv_forget) {
            activity.startActivity(new Intent(activity, FindPassWordActivity.class));

        } else {
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
        passWord = editPassword.getText().toString();
        return !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(passWord);
    }
}
