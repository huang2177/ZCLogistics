package com.myplas.l.source;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huangbrayant.citypicker.utils.StatusUtils;
import com.myplas.l.R;
import com.myplas.l.base.BaseActivity;
import com.myplas.l.base.BasePresenter;
import com.myplas.l.common.widgets.RoundImageView;

/**
 * 货主Activity
 *
 * @author Huangshuang  2018/4/8 0008
 */

public class CargoOwnerActivity extends BaseActivity {

    private ImageView ivBack;
    private LinearLayout layout;
    private RoundImageView ivHead;
    private RecyclerView recyclerView;
    private TextView tvName, tvTag1;

    private CargoOwnerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StatusUtils.setStatusBar(this, false, false);
        StatusUtils.setStatusTextColor(false, this);

        setContentView(R.layout.activity_cargo_owner);
    }

    @Override
    public void initView() {

        ivBack = findViewById(R.id.iv_back);
        tvTag1 = findViewById(R.id.cargo_tv_tag1);
        tvName = findViewById(R.id.cargo_tv_name);
        recyclerView = findViewById(R.id.cargo_lv);
        ivHead = findViewById(R.id.cargo_round_img);
        layout = findViewById(R.id.cargo_start_container);

        ivHead.setShapeType(1);
        tvTag1.setText("可抢单3");

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new CargoOwnerAdapter(this));

        initStart();
    }

    @Override
    public void setListener() {
        super.setListener();
        ivBack.setOnClickListener(this);
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    private void initStart() {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.ic_star_checked);
        layout.addView(imageView, 0);
    }
}
