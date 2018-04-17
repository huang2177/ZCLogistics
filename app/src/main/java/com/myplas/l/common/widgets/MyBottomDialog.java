package com.myplas.l.common.widgets;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import com.huangbrayant.citypicker.utils.StatusUtils;
import com.myplas.l.common.utils.ScreenUtils;

import static com.huangbrayant.citypicker.utils.StatusUtils.getStatusBarHeight;
import static com.myplas.l.common.utils.ScreenUtils.getScreenHeight;

/**
 * @author huangshuang
 */

public abstract class MyBottomDialog extends BottomSheetDialog implements View.OnClickListener {
    protected Activity activity;

    public MyBottomDialog(@NonNull Activity activity) {
        super(activity);
        this.activity = activity;

        setContentView(getLayoutId());

        initView();
        setListener();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //init();
    }

    /**
     * 处理在某些手机上面存在状态栏不透明的问题
     */
    private void init() {
        int screenHeight = getScreenHeight(activity);
        int statusBarHeight = getStatusBarHeight(getContext());
        int dialogHeight = screenHeight - statusBarHeight;
        if (getWindow() != null) {
            getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, dialogHeight == 0
                    ? ViewGroup.LayoutParams.MATCH_PARENT
                    : dialogHeight);
        }
    }


    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void setListener();

}
