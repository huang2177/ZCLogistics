package com.myplas.l.aspect;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.myplas.l.annotation.LoginInspect;
import com.myplas.l.base.ActivityManager;
import com.myplas.l.base.Constant;
import com.myplas.l.common.utils.SPUtil;
import com.myplas.l.common.utils.ToastUtil;
import com.myplas.l.common.widgets.CommonDialog;
import com.myplas.l.login.LoginActivity;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.runtime.reflect.Factory;

/**
 * @author Huangshuang  2018/4/10 0010
 */
@Aspect
public class LoginAspect implements CommonDialog.DialogClickListener {
    private SPUtil util;
    private boolean isLogin;
    private Activity context;
    private CommonDialog dialog;

    @Pointcut("execution(@com.myplas.l.annotation.LoginInspect * *(..))")
    public void methodAnnotatedWithLoginInspect() {

    }

    @Around("methodAnnotatedWithLoginInspect()")
    public Object joinPoint(ProceedingJoinPoint joinPoint) throws Throwable {

        context = ActivityManager.currentActivity();
        util = SPUtil.getInstance(context);
        isLogin = util.getBoolean(Constant.ISLOGINED);


        Object o = null;
        if (!isLogin) {
            if (dialog == null) {
                dialog = new CommonDialog.Builder()
                        .message("您还没有登录晨运宝！")
                        .type(1)
                        .listener(this)
                        .title("晨运宝")
                        .canceledOnTouchOutside(false)
                        .create(context);
                dialog.show();
            } else {
                dialog.show();
            }
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            String flag = methodSignature.getMethod().getAnnotation(LoginInspect.class).flag();
        } else {
            o = joinPoint.proceed();
        }
        return o;
    }

    @Override
    public void dialogClick(int flag, int type) {
        if (type == 1 && flag == CommonDialog.CONFIRM) {
            context.startActivity(new Intent(context, LoginActivity.class));
        }
    }
}
