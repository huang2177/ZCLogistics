package com.module.source.aspect;

import android.app.Activity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.module.base.annotation.LoginInspect;
import com.module.base.base.ActivityManager;
import com.module.base.base.Constant;
import com.module.base.utils.SPUtil;
import com.module.base.widgets.CommonDialog;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;


/**
 * @author Huangshuang  2018/4/10 0010
 */
@Aspect
public class LoginAspect implements CommonDialog.DialogClickListener {
    private SPUtil util;
    private boolean isLogin;
    private Activity context;
    private CommonDialog dialog;

    @Pointcut("execution(@com.module.base.annotation.LoginInspect * *(..))")
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
            ARouter.getInstance().build(Constant.PATH_LOGINACTIVITY).navigation();
        }
    }
}
