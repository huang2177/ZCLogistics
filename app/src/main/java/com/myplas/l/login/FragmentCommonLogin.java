package com.myplas.l.login;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.myplas.l.R;
import com.myplas.l.base.BaseFragment;
import com.myplas.l.base.Constant;
import com.myplas.l.common.utils.SPUtil;
import com.myplas.l.common.widgets.MyEditText;
import com.myplas.l.main.MainActivity;

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
        switch (v.getId()) {
            case R.id.btn_login:
                SPUtil.getInstance(activity).setBooloean(Constant.ISLOGINED, true);
                activity.startActivity(new Intent(activity, MainActivity.class));
                activity.finish();
                break;
            case R.id.tv_register:
                activity.startActivity(new Intent(activity, RegisterActivity.class));
                break;
            case R.id.tv_forget:
                activity.startActivity(new Intent(activity, FindPassWordActivity.class));
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
        passWord = editPassword.getText().toString();
        return !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(passWord);
    }
}
