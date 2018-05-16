package com.myplas.l.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.WindowManager;

import com.module.base.base.Constant;
import com.module.base.utils.SPUtil;
import com.module.login.LoginOrRegisterActivity;


/**
 * @author Huangshuang
 *         2018/3/8 0008
 */

public class SplashActivity extends Activity {
    boolean isGuided, isLogined;
    private MyThread mMyThread = new MyThread();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        isGuided = SPUtil.getInstance(this).getBoolean(Constant.ISGUIDED);
        isLogined = SPUtil.getInstance(this).getBoolean(Constant.ISLOGINED);
        mMyThread.start();
    }

    public void handle() {
        if (!isGuided) {
            startActivity(new Intent(SplashActivity.this, GuideActivity.class));
        } else {
            if (isLogined) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            } else {
                startActivity(new Intent(SplashActivity.this, LoginOrRegisterActivity.class));
            }
        }
        finish();
        if (isGuided && isLogined) {
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }

    private class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                Thread.sleep(2000);
                handle();
            } catch (Exception e) {

            }
        }
    }
}
