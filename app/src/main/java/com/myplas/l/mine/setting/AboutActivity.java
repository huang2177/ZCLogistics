package com.myplas.l.mine.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.myplas.l.R;
import com.myplas.l.base.BaseActivity;
import com.myplas.l.base.BasePresenter;
import com.myplas.l.common.widgets.VersionUtil;

/**
 * @author Huangshuang  2018/3/28 0028
 */

public class AboutActivity extends BaseActivity {

    private TextView tvVersion, tvPhone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        title("关于晨运宝");
    }

    @Override
    public void initView() {
        tvPhone = findViewById(R.id.about_phone);
        tvVersion = findViewById(R.id.about_version);

        tvVersion.setText("Android " + VersionUtil.getVersionName(this));
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }
}
