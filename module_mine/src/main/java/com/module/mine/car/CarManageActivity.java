package com.module.mine.car;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.module.base.base.BaseActivity;
import com.module.base.base.BasePresenter;
import com.module.base.base.Constant;
import com.module.mine.R;

/**
 * @author Huangshuang  2018/3/30 0030
 */

public class CarManageActivity extends BaseActivity {

    private ListView listView;
    private CarManageAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_manager);

        title("车辆管理").rightText("添加车辆");
    }

    @Override
    public void initView() {

        listView = findViewById(R.id.car_manager_lv);
        listView.setAdapter(new CarManageAdapter(this));

    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onRightClick() {
        ARouter.getInstance()
                .build(Constant.PATH_COMPLETECARINFOACTIVITY)
                .withBoolean(Constant.FLAG, false)
                .navigation();
    }
}
