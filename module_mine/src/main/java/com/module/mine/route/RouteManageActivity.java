package com.module.mine.route;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.module.base.base.BaseActivity;
import com.module.base.base.BasePresenter;
import com.module.mine.R;

/**
 * @author Huangshuang  2018/3/30 0030
 */

public class RouteManageActivity extends BaseActivity {

    private Button btnCommit;
    private ListView listView;
    private RouteManageAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_manager);

        title("我的常用线路");
    }

    @Override
    public void initView() {

        listView = findViewById(R.id.route_manager_lv);
        btnCommit = findViewById(R.id.route_manager_btn);
        listView.setAdapter(new RouteManageAdapter(this));

    }

    @Override
    public void setListener() {
        super.setListener();
        btnCommit.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.route_manager_btn) {
            startActivity(new Intent(this, AddRouteActivity.class));
        }
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

}
