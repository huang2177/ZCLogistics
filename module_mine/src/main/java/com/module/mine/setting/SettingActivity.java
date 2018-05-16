package com.module.mine.setting;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.module.base.base.BaseActivity;
import com.module.base.base.BasePresenter;
import com.module.base.base.Constant;
import com.module.base.utils.SPUtil;
import com.module.base.utils.ToastUtil;
import com.module.base.widgets.muiltlayout.CustomAdapter;
import com.module.base.widgets.muiltlayout.CustomProvider;
import com.module.mine.R;

import java.util.Arrays;

/**
 * @author Huangshuang  2018/3/20 0020
 */

public class SettingActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assets);

        title("设置");
    }

    @Override
    public void initView() {

        listView = findViewById(R.id.assets_lv);

        CustomProvider provider = new CustomProvider(this)
                .leftTexts(Arrays.asList("修改密码", "去打分", "关于晨运宝"))
                .centerImgs(Arrays.asList(0, 0, R.drawable.ic_new_version))
                .leftImgs(Arrays.asList(R.drawable.ic_modify, R.drawable.ic_score, R.drawable.ic_about))
                .rightImgs(Arrays.asList(R.drawable.ic_arrow_right_gray, R.drawable.ic_arrow_right_gray, R.drawable.ic_arrow_right_gray));

        adapter = new CustomAdapter(provider);
        listView.setAdapter(adapter);
    }

    @Override
    public void setListener() {
        super.setListener();
        listView.setOnItemClickListener(this);
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            SPUtil.getInstance(this).setBooloean(Constant.ISLOGINED, false);
            ARouter.getInstance().build(Constant.PATH_MAINACTIVITY).navigation();
        } else if (position == 1) {
            startToMarket();
        } else {
            startActivity(new Intent(this, AboutActivity.class));
        }
    }

    /**
     * 跳转到应用市场
     */
    private void startToMarket() {
        try {
            Uri uri = Uri.parse("market://details?id=" + getPackageName());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (Exception e) {
            ToastUtil.show(this, "未能在应用市场找到相关应用！");
        }
    }
}



