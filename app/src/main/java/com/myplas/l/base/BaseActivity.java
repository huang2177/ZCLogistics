package com.myplas.l.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.myplas.l.R;

/**
 * @author huangshuang
 * @date 2018/3/8 0008
 */

public abstract class BaseActivity<V, T extends BasePresenter<V>> extends FragmentActivity implements View.OnClickListener {
    protected BasePresenter<V> presenter;

    private ImageView ivLeft;
    private TextView tvTitle, tvRight;
    protected FrameLayout titleBarContainer;


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        initView();
        initTitleBar();
        setListener();

        presenter = createPresenter();
        if (presenter != null) {
            presenter.attachView((V) this);
            presenter.fetch();
        }
    }

    /**
     * 初始化titleBar
     */
    public BaseActivity initTitleBar() {
        tvTitle = findViewById(R.id.titleBar_title);
        ivLeft = findViewById(R.id.titleBar_left_img);
        tvRight = findViewById(R.id.titleBar_right_text);
        titleBarContainer = findViewById(R.id.titleBar_container);
        return this;
    }

    public BaseActivity titleBarBackground(int resColor) {
        int color = ContextCompat.getColor(this, resColor);
        titleBarContainer.setBackgroundColor(color);
        return this;
    }

    public BaseActivity leftImageRes(int drawableRes) {
        ivLeft.setImageResource(drawableRes);
        return this;
    }

    public BaseActivity rightImageRes(int drawableRes) {
        tvRight.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawableRes, 0);
        return this;
    }

    public BaseActivity title(String title) {
        tvTitle.setText(title);
        return this;
    }

    public BaseActivity rightText(String text) {
        tvRight.setText(text);
        return this;
    }

    public BaseActivity titleColor(int resColor) {
        int color = ContextCompat.getColor(this, resColor);
        tvTitle.setTextColor(color);
        return this;
    }


    /**
     * 用于实现类初始化控件
     */
    public abstract void initView();

    /**
     * 用于实现类设置监听
     */
    @CallSuper
    public void setListener() {
        if (ivLeft != null) {
            ivLeft.setOnClickListener(this);
        }
        if (tvRight != null) {
            tvRight.setOnClickListener(this);
        }
    }

    /**
     * 用于实现类创建presenter
     *
     * @return
     */
    public abstract BasePresenter<V> createPresenter();


    /**
     * 右侧图片的点击事件
     */
    public void onRightClick() {

    }

    /**
     * 弹出软键盘
     *
     * @param view
     */
    public void showInPutKeybord(final View view) {
        view.requestFocus();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.showSoftInput(view, 0);
                }
            }
        }, 100);
    }

    @CallSuper
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titleBar_left_img:
                finish();
                break;
            case R.id.titleBar_right_text:
                onRightClick();
                break;
            default:
                break;
        }
    }
}
