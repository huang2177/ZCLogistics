package com.myplas.l.source;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
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

public class DriverActivity extends BaseActivity {

    private ImageView ivBack;
    private LinearLayout layout;
    private RoundImageView ivHead;
    private RecyclerView recyclerView;
    private TextView tvName, tvTag1, tvTag2;

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
        tvName = findViewById(R.id.cargo_tv_name);
        tvTag1 = findViewById(R.id.cargo_tv_tag1);
        tvTag2 = findViewById(R.id.cargo_tv_tag2);
        recyclerView = findViewById(R.id.cargo_lv);
        ivHead = findViewById(R.id.cargo_round_img);
        layout = findViewById(R.id.cargo_start_container);

        ivHead.setShapeType(1);
        tvTag2.setVisibility(View.VISIBLE);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new DriverCarInfoAdapter(this));

        initStart();
    }

    @Override
    public void setListener() {
        super.setListener();
        ivBack.setOnClickListener(this);
        tvTag1.setOnClickListener(this);
        tvTag2.setOnClickListener(this);
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.cargo_tv_tag1:
                tvTag2.setBackgroundResource(R.drawable.bg_owner_tab_gray);
                tvTag1.setBackgroundResource(R.drawable.bg_owner_tab_orange);
                tvTag1.setTextColor(ContextCompat.getColor(this, R.color.black));
                tvTag2.setTextColor(ContextCompat.getColor(this, R.color.defaultWhite));

                recyclerView.setAdapter(new DriverCarInfoAdapter(this));
                break;
            case R.id.cargo_tv_tag2:
                tvTag1.setBackgroundResource(R.drawable.bg_owner_tab_gray);
                tvTag2.setBackgroundResource(R.drawable.bg_owner_tab_orange);
                tvTag1.setTextColor(ContextCompat.getColor(this, R.color.black));
                tvTag2.setTextColor(ContextCompat.getColor(this, R.color.defaultWhite));

                recyclerView.setAdapter(new DriverRouteAdapter(this));
                break;
            default:
                break;
        }
    }

    private void initStart() {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.ic_star_checked);
        layout.addView(imageView, 0);
    }
}
