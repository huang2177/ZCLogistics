package com.module.mine.personinfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.module.base.base.BaseActivity;
import com.module.base.base.BasePresenter;
import com.module.mine.R;

/**
 * @author Huangshuang  2018/3/20 0020
 */

public class MyInfoActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private ListView mListview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfo);
        title("个人资料");
    }

    @Override
    public void initView() {
        mListview = findViewById(R.id.myinfo_lv);
        mListview.setAdapter(new MyInfoAdapter(this));
    }

    @Override
    public void setListener() {
        super.setListener();
        mListview.setOnItemClickListener(this);
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
