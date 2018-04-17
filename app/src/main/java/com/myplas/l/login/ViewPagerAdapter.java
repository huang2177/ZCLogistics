package com.myplas.l.login;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.myplas.l.base.BaseFragment;

import java.util.List;

/**
 * 编写： 黄双
 * 电话：15378412400
 * 邮箱：15378412400@163.com
 * 时间：2017/3/17 14:52
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    private List<String> stringList;

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> list, List<String> stringList) {
        super(fm);
        this.list = list;
        this.stringList = stringList;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }


    @Override
    public int getCount() {
        if (list.size() != 0) {
            return list.size();
        }
        return 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return stringList.get(position);
    }
}
