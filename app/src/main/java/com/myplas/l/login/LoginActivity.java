package com.myplas.l.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.flyco.tablayout.SlidingTabLayout;
import com.huangbrayant.citypicker.utils.StatusUtils;
import com.myplas.l.R;
import com.myplas.l.base.BaseActivity;
import com.myplas.l.base.BasePresenter;
import com.myplas.l.base.Constant;
import com.myplas.l.common.listener.OnKeyboardChangeListener;
import com.myplas.l.common.utils.KeyboardHelper;
import com.myplas.l.common.utils.SPUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Huangshuang  2018/3/8 0008
 */

public class LoginActivity extends BaseActivity {
    private ImageView ivLogo;
    private LinearLayout root;
    private ViewPager viewPager;
    private SlidingTabLayout tabLayout;

    private ViewPagerAdapter adapter;
    private List<String> stringList;
    private List<Fragment> fragmentsList;

    private FragmentCommonLogin commonLogin;
    private FragmentQuicklyLogin quicklyLogin;
    private KeyboardHelper mKeyboardHelper;
    private OnKeyboardChangeListener mKeyboardListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StatusUtils.setStatusBar(this, false, false);
        StatusUtils.setStatusTextColor(true, this);

        setContentView(R.layout.activity_login);

    }


    @Override
    public void initView() {
        root = findViewById(R.id.root_login);
        ivLogo = findViewById(R.id.iv_login);
        viewPager = findViewById(R.id.vp_login);
        tabLayout = findViewById(R.id.tablayout_login);

        mKeyboardHelper = new KeyboardHelper(this, root);
        mKeyboardHelper.enable();

        commonLogin = new FragmentCommonLogin();
        quicklyLogin = new FragmentQuicklyLogin();

        fragmentsList = new ArrayList<>();
        stringList = Arrays.asList("普通登录", "手机快捷登录");

        fragmentsList.add(commonLogin);
        fragmentsList.add(quicklyLogin);
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentsList, stringList);
        viewPager.setAdapter(adapter);
        tabLayout.setViewPager(viewPager);
    }



    @Override
    public BasePresenter createPresenter() {
        return null;
    }


}
