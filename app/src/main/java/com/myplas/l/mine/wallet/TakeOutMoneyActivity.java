package com.myplas.l.mine.wallet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.myplas.l.R;
import com.myplas.l.base.BaseActivity;
import com.myplas.l.base.BasePresenter;
import com.myplas.l.common.widgets.MyEditText;

import java.util.Arrays;
import java.util.List;

/**
 * @author Huangshuang  2018/3/29 0029
 */

public class TakeOutMoneyActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private Button btnCommit;
    private ViewPager viewPager;
    private MyEditText editText;
    private TextView tvAll, tvUsable;

    private TakeMoneyAdapter adapter;
    private List<Integer> mViewLists;

    private TakeMoneyDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_money);

        title("提现");
    }

    @Override
    public void initView() {
        viewPager = findViewById(R.id.take_money_vp);
        tvAll = findViewById(R.id.take_money_tv_all);
        btnCommit = findViewById(R.id.take_money_btn);
        editText = findViewById(R.id.take_money_edit);
        tvUsable = findViewById(R.id.take_money_tv_usable);


        mViewLists = Arrays.asList(R.drawable.bg_agricultural
                , R.drawable.bg_industrial_and_commercial
                , R.drawable.bg_agricultural
                , R.drawable.bg_industrial_and_commercial);

        adapter = new TakeMoneyAdapter(this, mViewLists);
        viewPager.setAdapter(adapter);

    }

    @Override
    public void setListener() {
        super.setListener();
        tvAll.setOnClickListener(this);
        btnCommit.setOnClickListener(this);
        viewPager.addOnPageChangeListener(this);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.take_money_btn:
                dialog = new TakeMoneyDialog(this);
                dialog.setMoney(editText.getText().toString());
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                break;
            case R.id.take_money_tv_all:

                break;
            default:
                break;
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
