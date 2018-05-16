package com.module.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.WindowManager;

import com.huang.citypicker.utils.StatusUtils;
import com.module.base.base.BaseActivity;
import com.module.base.base.BasePresenter;

/**
 * @author Huangshuang  2018/3/9 0009
 */

public class LoginOrRegisterActivity extends BaseActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_register);

        StatusUtils.setStatusBar(this, false, false);
        StatusUtils.setStatusTextColor(false, this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }


    @Override
    public void initView() {

    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }


    /**
     * 登录
     *
     * @param view
     */
    public void login(View view) {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    /**
     * 注册
     *
     * @param view
     */
    public void register(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }

}
