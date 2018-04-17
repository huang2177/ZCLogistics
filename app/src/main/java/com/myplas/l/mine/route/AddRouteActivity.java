package com.myplas.l.mine.route;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.myplas.l.R;
import com.myplas.l.base.BaseActivity;
import com.myplas.l.base.BasePresenter;

/**
 * @author Huangshuang  2018/3/30 0030
 */

public class AddRouteActivity extends BaseActivity {

    private Button btnCommit;
    private TextView tvCarModel;
    private ImageView ivStart, ivEnd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_route);

        title("添加线路");
    }

    @Override
    public void initView() {

        ivEnd = findViewById(R.id.add_route_end);
        ivStart = findViewById(R.id.add_route_start);
        btnCommit = findViewById(R.id.add_route_btn);
        tvCarModel = findViewById(R.id.add_route_car_model);

    }

    @Override
    public void setListener() {
        super.setListener();
        ivEnd.setOnClickListener(this);
        ivStart.setOnClickListener(this);
        btnCommit.setOnClickListener(this);
        tvCarModel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.add_route_btn:

                break;
            case R.id.add_route_end:

                break;
            case R.id.add_route_start:

                break;
            case R.id.add_route_car_model:
                startActivity(new Intent(this,ChooseCarModelActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }
}
