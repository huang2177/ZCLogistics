package com.myplas.l.mine.route;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.telephony.CellIdentityGsm;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.myplas.l.R;
import com.myplas.l.base.BaseActivity;
import com.myplas.l.base.BasePresenter;
import com.myplas.l.common.utils.ToastUtil;
import com.myplas.l.common.widgets.XGridView;
import com.myplas.l.source.PopupWindowAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Huangshuang  2018/3/30 0030
 */

public class ChooseCarModelActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private Button btnCommit;
    private XGridView gvCarModel, gvCarLength;

    private List<String> carModelList;
    private List<String> carLengthList;
    private PopupWindowAdapter carModelAdapter;
    private PopupWindowAdapter carLengthAdapter;

    private List<String> selectModelList;
    private List<String> selectLengthList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_car_model);

        title("选择车辆类型");
    }

    @Override
    public void initView() {
        btnCommit = findViewById(R.id.choose_btn);
        gvCarModel = findViewById(R.id.choose_car_model_gv);
        gvCarLength = findViewById(R.id.choose_car_length_gv);

        selectModelList = new ArrayList<>();
        selectLengthList = new ArrayList<>();

        initGridView();
    }

    private void initGridView() {
        carModelList = Arrays.asList("全部"
                , "高栏"
                , "平板"
                , "箱式"
                , "高地板"
                , "自卸"
                , "冷藏"
                , "危险品"
                , "其他");
        carModelAdapter = new PopupWindowAdapter(this, carModelList);
        gvCarModel.setAdapter(carModelAdapter);

        carLengthList = Arrays.asList("全部"
                , "<4.2米"
                , "4.2米"
                , "5米"
                , "6.2米"
                , "7.2米"
                , "7.7米"
                , "7.8米"
                , "8.2米"
                , "8.7米"
                , "9.6米"
                , "12.5米"
                , "13米"
                , "15米"
                , "16米"
                , "17.5米");
        carLengthAdapter = new PopupWindowAdapter(this, carLengthList);
        gvCarLength.setAdapter(carLengthAdapter);

        selectModelList.add(carModelList.get(0));
        selectLengthList.add(carLengthList.get(0));
    }

    @Override
    public void setListener() {
        super.setListener();
        btnCommit.setOnClickListener(this);
        gvCarModel.setOnItemClickListener(this);
        gvCarLength.setOnItemClickListener(this);
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.choose_btn) {
            finish();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.choose_car_model_gv) {
            if (carModelAdapter.clickFlags.get(position)) {
                selectModelList.remove(carModelList.get(position));
            } else {
                selectModelList.add(carModelList.get(position));
                if (selectModelList.size() > 3) {
                    selectModelList.remove(carModelList.get(position));
                    ToastUtil.show(this, "最多只能选择3个！");
                    return;
                }
            }
            carModelAdapter.selectItem(position, false);
        } else {
            if (carLengthAdapter.clickFlags.get(position)) {
                selectLengthList.remove(carLengthList.get(position));
            } else {
                selectLengthList.add(carLengthList.get(position));
                if (selectLengthList.size() > 3) {
                    selectLengthList.remove(carLengthList.get(position));
                    ToastUtil.show(this, "最多只能选择3个！");
                    return;
                }
            }
            carLengthAdapter.selectItem(position, false);
        }
    }
}
