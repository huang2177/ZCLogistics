package com.myplas.l.main;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NotificationCompat;
import android.view.KeyEvent;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.module.base.base.BaseActivity;
import com.module.base.base.BasePresenter;
import com.module.base.base.Constant;
import com.module.base.listener.OnTabSelectedListener;
import com.module.base.utils.ToastUtil;
import com.module.base.widgets.MyViewPager;
import com.module.mine.MineFragment;
import com.module.msg.message.MessageFragment;
import com.module.source.SourceFragment;
import com.myplas.l.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huangshuang
 */
@Route(path = Constant.PATH_MAINACTIVITY)
public class MainActivity extends BaseActivity implements OnTabSelectedListener.TabSelectedListener {

    private MyViewPager myViewPager;
    private BottomNavigationBar navigationBar;
    private NavigationBarHelper mNavigationBarHelper;

    private List<Fragment> fragments;
    private long exitTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        myViewPager.setCurrentItem(0);
        mNavigationBarHelper.init(this, navigationBar, this);

    }

    @Override
    public void initView() {
        myViewPager = findViewById(R.id.viewpager);
        navigationBar = findViewById(R.id.navigation_bar);
        mNavigationBarHelper = NavigationBarHelper.getInstance();

        initViewPager();
        mNavigationBarHelper.init(this, navigationBar, this);
    }

    /**
     * 初始化viewpager
     */
    private void initViewPager() {
        fragments = new ArrayList<>();
        SourceFragment sourceFragment = new SourceFragment();
        MessageFragment messageFragment = new MessageFragment();
        MineFragment mineFragment = new MineFragment();

        fragments.add(sourceFragment);
        fragments.add(messageFragment);
        fragments.add(mineFragment);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        myViewPager.setAdapter(adapter);
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onTabSelected(int position) {
        myViewPager.setCurrentItem(position);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 退出应用
     */
    private void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            ToastUtil.show(getApplicationContext(), "再按一次晨运宝!");
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
