package com.myplas.l.main;

import android.content.Context;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.module.base.annotation.LoginInspect;
import com.module.base.base.Constant;
import com.module.base.listener.OnTabSelectedListener;
import com.module.base.utils.SPUtil;
import com.myplas.l.R;

/**
 * @author Huangshuang  2018/4/12 0012
 */

public class NavigationBarHelper implements BottomNavigationBar.OnTabSelectedListener {

    private static final NavigationBarHelper ourInstance = new NavigationBarHelper();

    private SPUtil util;
    private boolean isLogin;
    private OnTabSelectedListener.TabSelectedListener listener;

    private BottomNavigationItem itemSource, itemMessage, itemMine;

    private NavigationBarHelper() {

    }

    public static NavigationBarHelper getInstance() {
        return ourInstance;
    }

    public void init(Context context, BottomNavigationBar navigationBar
            , OnTabSelectedListener.TabSelectedListener listener) {
        this.listener = listener;
        util = SPUtil.getInstance(context);

        init(navigationBar);
    }

    /**
     * 初始化底部导航栏
     */
    private void init(BottomNavigationBar navigationBar) {

        if (navigationBar == null) {
            return;
        }
        navigationBar.clearAll();

        TextBadgeItem badgeItem = new TextBadgeItem().setText("1");

        itemMine = new BottomNavigationItem(R.drawable.ic_tabbar_me_hl, "我的");

        itemMessage = new BottomNavigationItem(R.drawable.ic_tabbar_msg_hl, "消息")
                .setBadgeItem(badgeItem);

        itemSource = new BottomNavigationItem(R.drawable.ic_tabbar_supply_hl, "货源");

        setActiveColorByLoginStatus();

        navigationBar.addItem(itemSource)
                .addItem(itemMessage)
                .addItem(itemMine)
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setFirstSelectedPosition(0)
                .initialise();

        navigationBar.setTabSelectedListener(this);
    }

    /**
     * 根据是否登录来设置底部Icon的颜色
     */
    private void setActiveColorByLoginStatus() {

        isLogin = util.getBoolean(Constant.ISLOGINED);
        if (isLogin) {
            itemMine.setActiveColorResource(R.color.mainColor)
                    .setInActiveColorResource(R.color.littleGray);

            itemMessage.setActiveColorResource(R.color.mainColor)
                    .setInActiveColorResource(R.color.littleGray);

            itemSource.setActiveColorResource(R.color.mainColor)
                    .setInActiveColorResource(R.color.littleGray);
        } else {
            itemMine.setActiveColorResource(R.color.littleGray)
                    .setInActiveColorResource(R.color.littleGray);

            itemMessage.setActiveColorResource(R.color.littleGray)
                    .setInActiveColorResource(R.color.littleGray);

            itemSource.setActiveColorResource(R.color.mainColor)
                    .setInActiveColorResource(R.color.mainColor);
        }
    }

    @LoginInspect(flag = "")
    @Override
    public void onTabSelected(int position) {
        if (listener != null) {
            listener.onTabSelected(position);
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @LoginInspect(flag = "")
    @Override
    public void onTabReselected(int position) {

    }
}
