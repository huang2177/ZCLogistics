package com.module.source;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.module.base.base.BaseActivity;
import com.module.base.base.BasePresenter;
import com.module.base.base.Constant;
import com.module.base.widgets.RoundImageView;
import com.module.base.widgets.XListView;

/**
 * @author Huangshuang  2018/3/19 0019
 */
@Route(path = Constant.PATH_SOURCEDETAILACTIVITY)
public class SourceDetailActivity extends BaseActivity {

    private Button btnComit;
    private RoundImageView ivHead;
    private LinearLayout layoutBaseInfo;
    private XListView lvSource, lvSupply;
    private TextView tvName, tvCompany, tvSupplyNum, tvStauts;

    private SourceDetailAdapter detailAdapter;
    private SourceSupplyAdapter supplyAdapter;

    private boolean isExpanded;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source_detail);

        title("货源详情").rightImageRes(R.drawable.ic_btn_phone);
    }

    @Override
    public void initView() {
        ivHead = findViewById(R.id.detail_iv_head);
        tvName = findViewById(R.id.detail_tv_name);
        lvSupply = findViewById(R.id.detail_lv_supply);
        lvSource = findViewById(R.id.detail_lv_source);
        btnComit = findViewById(R.id.detail_btn_commit);
        tvCompany = findViewById(R.id.detail_tv_company);
        layoutBaseInfo = findViewById(R.id.detail_baseinfo);
        tvSupplyNum = findViewById(R.id.detail_tv_supply_num);
        tvStauts = findViewById(R.id.detail_tv_layout_stauts);

        ivHead.setTagEnable(true);
        layoutBaseInfo.setBackgroundColor(ContextCompat.getColor(this, R.color.mainColor));

        detailAdapter = new SourceDetailAdapter(this);
        lvSource.setAdapter(detailAdapter);

        supplyAdapter = new SourceSupplyAdapter(this);
        lvSupply.setAdapter(supplyAdapter);
    }


    @Override
    public void setListener() {
        super.setListener();
        ivHead.setOnClickListener(this);
        tvStauts.setOnClickListener(this);
        btnComit.setOnClickListener(this);
        layoutBaseInfo.setOnClickListener(this);
        lvSupply.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SourceDetailActivity.this, DriverActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int i = v.getId();
        if (i == R.id.detail_iv_head) {
        } else if (i == R.id.detail_btn_commit) {
            startActivity(new Intent(this, QuotePriceActivity.class));

        } else if (i == R.id.detail_tv_layout_stauts) {
            expandOrClose();

        } else if (i == R.id.detail_baseinfo) {
            startActivity(new Intent(this, CargoOwnerActivity.class));

        } else {
        }

    }

    /**
     * 展开或者收起listView时候改变文字信息
     */
    private void expandOrClose() {
        isExpanded = !isExpanded;
        detailAdapter.expandListView(isExpanded);
        tvStauts.setText(isExpanded ? "收起" : "展开");
        tvStauts.setCompoundDrawablesWithIntrinsicBounds(0, 0, isExpanded
                ? R.drawable.ic_arrow_up_gray
                : R.drawable.ic_arrow_down_gray, 0);
    }
}
