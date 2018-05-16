package com.myplas.l.main;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.huang.citypicker.utils.StatusUtils;
import com.module.base.utils.SPUtil;
import com.module.login.LoginOrRegisterActivity;
import com.module.base.base.BaseActivity;
import com.module.base.base.BasePresenter;
import com.module.base.base.Constant;
import com.myplas.l.R;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Huangshuang
 */
public class GuideActivity extends BaseActivity {
    private List<ImageView> viewList;

    private ViewPager viewPager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void initView() {
        StatusUtils.setStatusBar(this, false, false);
        StatusUtils.setStatusTextColor(false, this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        viewList = new ArrayList<>();
        viewPager = findViewById(R.id.vp);

        ImageView ivFirst = new ImageView(this);
        ivFirst.setScaleType(ImageView.ScaleType.FIT_XY);
        ivFirst.setImageResource(R.drawable.img_guide1);

        ImageView ivSecond = new ImageView(this);
        ivSecond.setScaleType(ImageView.ScaleType.FIT_XY);
        ivSecond.setImageResource(R.drawable.img_guide2);

        viewList.add(ivFirst);
        viewList.add(ivSecond);
        viewPager.setAdapter(new GuideAdapter(viewList));

        /**
         * 点击跳转到MainActivity
         */
        ivSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPUtil.getInstance(GuideActivity.this).setBooloean(Constant.ISGUIDED, true);
                startActivity(new Intent(GuideActivity.this, LoginOrRegisterActivity.class));
                finish();
            }
        });
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

    }

    private class GuideAdapter extends PagerAdapter {
        private List<ImageView> mViewList;

        public GuideAdapter(List<ImageView> mViewList) {
            this.mViewList = mViewList;
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
            container.addView(mViewList.get(position));
            return mViewList.get(position);
        }

        //该方法的功能是移除一个给定位置的页面。
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}