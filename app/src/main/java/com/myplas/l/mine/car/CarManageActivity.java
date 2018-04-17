package com.myplas.l.mine.car;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.myplas.l.R;
import com.myplas.l.base.BaseActivity;
import com.myplas.l.base.BasePresenter;
import com.myplas.l.base.Constant;
import com.myplas.l.login.CompleteCarInfoActivity;

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
        Intent intent = new Intent(this, CompleteCarInfoActivity.class);
        intent.putExtra(Constant.FLAG, false);
        startActivity(intent);
    }
}
