package com.myplas.l.source;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myplas.l.R;
import com.myplas.l.base.BaseActivity;
import com.myplas.l.base.BasePresenter;
import com.myplas.l.common.widgets.RoundImageView;
import com.myplas.l.common.widgets.XListView;

/**
 * @author Huangshuang  2018/3/19 0019
 */

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
        switch (v.getId()) {
            case R.id.detail_iv_head:
                break;
            case R.id.detail_btn_commit:
                startActivity(new Intent(this, QuotePriceActivity.class));
                break;
            case R.id.detail_tv_layout_stauts:
                expandOrClose();
                break;
            case R.id.detail_baseinfo:
                startActivity(new Intent(this, CargoOwnerActivity.class));
                break;
            default:
                break;
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
