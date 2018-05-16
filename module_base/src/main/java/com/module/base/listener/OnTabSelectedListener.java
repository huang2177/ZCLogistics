package com.module.base.listener;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;

/**
 * @author Huangshuang  2018/3/15 0015
 */

public class OnTabSelectedListener implements BottomNavigationBar.OnTabSelectedListener {

    private TabSelectedListener listener;

    public OnTabSelectedListener(TabSelectedListener listener) {
        this.listener = listener;
    }

    @Override
    public void onTabSelected(int position) {
        if (listener != null) {
            listener.onTabSelected(position);
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    public interface TabSelectedListener {
        void onTabSelected(int position);
    }
}
