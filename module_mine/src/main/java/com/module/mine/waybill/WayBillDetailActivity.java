package com.module.mine.waybill;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.module.base.base.BaseActivity;
import com.module.base.base.BasePresenter;
import com.module.base.widgets.RoundImageView;
import com.module.base.widgets.XListView;
import com.module.mine.R;
import com.yanzhenjie.album.Album;

/**
 * @author Huangshuang  2018/3/19 0019
 */

public class WayBillDetailActivity extends BaseActivity {

    private RoundImageView ivHead;
    private LinearLayout layoutBaseInfo;
    private XListView lvSource, lvSupply;
    private TextView tvName, tvCompany, tvSupplyNum, remarks1, remarks2, remarks3, tvType1, tvType2;

    private WayBillDetailAdapter detailAdapter;
    //private SourceSupplyAdapter supplyAdapter;

    private int white, black, blue;
    public final int REQUESTCODE = 100;

    private CertificateDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waybill_detail);

        title("承运单详情").rightImageRes(R.drawable.ic_btn_phone);
    }

    @Override
    public void initView() {
        ivHead = findViewById(R.id.detail_iv_head);
        tvName = findViewById(R.id.detail_tv_name);
        tvCompany = findViewById(R.id.detail_tv_company);
        layoutBaseInfo = findViewById(R.id.detail_baseinfo);
        tvType1 = findViewById(R.id.waybill_detail_tv_type1);
        tvType2 = findViewById(R.id.waybill_detail_tv_type2);
        lvSupply = findViewById(R.id.waybill_detail_lv_supply);
        lvSource = findViewById(R.id.waybill_detail_lv_source);
        tvSupplyNum = findViewById(R.id.waybill_detail_tv_num);
        remarks1 = findViewById(R.id.waybill_detail_tv_remark1);
        remarks2 = findViewById(R.id.waybill_detail_tv_remark2);
        remarks3 = findViewById(R.id.waybill_detail_tv_remark3);

        black = ContextCompat.getColor(this, R.color.black);
        white = ContextCompat.getColor(this, R.color.defaultWhite);


        ivHead.setTagEnable(true);

        tvName.setTextColor(black);
        tvCompany.setTextColor(black);
        layoutBaseInfo.setBackgroundColor(white);

        detailAdapter = new WayBillDetailAdapter(this);
        lvSource.setAdapter(detailAdapter);

//        supplyAdapter = new SourceSupplyAdapter(this);
//        lvSupply.setAdapter(supplyAdapter);
    }


    @Override
    public void setListener() {
        super.setListener();
        ivHead.setOnClickListener(this);
        tvType1.setOnClickListener(this);
        tvType2.setOnClickListener(this);
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int i = v.getId();
        if (i == R.id.detail_iv_head) {
        } else if (i == R.id.waybill_detail_tv_type1) {
            uploadCertificate();

        } else if (i == R.id.waybill_detail_tv_type2) {
            uploadCertificate();

        } else {
        }
    }

    /**
     * 打开底部dialog
     */
    private void uploadCertificate() {
        if (dialog == null) {
            dialog = new CertificateDialog(this);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        } else {
            dialog.show();
        }
    }

    /**
     * 上传凭证
     */
    public void upload() {

    }


    @Override
    public void onRightClick() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == REQUESTCODE) {
                dialog.onPickImageResult(Album.parseResult(data).get(0));
            }
        } catch (Exception e) {

        }
    }
}
