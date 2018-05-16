package com.module.mine.wallet;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.module.mine.R;

import java.util.List;

/**
 * @author Huangshuang  2018/3/15 0015
 */

public class TakeMoneyAdapter extends PagerAdapter {
    private List<Integer> mViewList;
    private Context context;

    public TakeMoneyAdapter(Context context, List<Integer> mViewList) {
        this.mViewList = mViewList;
        this.context = context;
    }

    @Override
    public int getCount() {
        //返回有效的View的个数
        return mViewList.size();
    }

    //判断是否page view与 instantiateItem(ViewGroup, int)返回的object的key 是否相同，以提供给其他的函数使用
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    //instantiateItem该方法的功能是创建指定位置的页面视图。finishUpdate(ViewGroup)返回前，页面应该保证被构造好
    //返回值：返回一个对应该页面的object，这个不一定必须是View，但是应该是对应页面的一些其他容器
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View viewRoot = LayoutInflater.from(context).inflate(R.layout.item_take_money, null);
        container.addView(viewRoot);
        iniyView(viewRoot,position);
        return viewRoot;
    }

    //该方法的功能是移除一个给定位置的页面。
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


    private void iniyView(View viewRoot, int position) {
        TextView textView = viewRoot.findViewById(R.id.item_take_money_tv);
        ImageView imageView = viewRoot.findViewById(R.id.item_take_money_iv);

        imageView.setImageResource(mViewList.get(position));
    }
}
