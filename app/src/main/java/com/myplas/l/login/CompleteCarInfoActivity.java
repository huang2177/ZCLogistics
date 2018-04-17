package com.myplas.l.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huangbrayant.citypicker.utils.StatusUtils;
import com.myplas.l.base.Constant;
import com.myplas.l.main.MainActivity;
import com.myplas.l.R;
import com.myplas.l.base.ActivityManager;
import com.myplas.l.base.BaseActivity;
import com.myplas.l.base.BasePresenter;
import com.myplas.l.common.utils.KeyboardHelper;
import com.myplas.l.common.widgets.CommonDialog;
import com.myplas.l.common.widgets.MyEditText;
import com.myplas.l.common.widgets.XListView;

import java.util.Arrays;
import java.util.List;

/**
 * @author Huangshuang  2018/3/13 0013
 */

public class CompleteCarInfoActivity extends BaseActivity {

    private View space;
    private Button btnCommit;
    private ImageView ivStep;
    private XListView listView;
    private LinearLayout layoutRoot;

    private List<String> stringList;

    private KeyboardHelper mKeyboardHelper;
    private CarInfoAdapter adapter;

    private SparseArray<String> sparseArray;

    private CommonDialog dialog;

    /**
     * 标记是否从注册跳转过来
     */
    private boolean fromRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_complete_carinfo);

        getFromWhere();
    }

    private void getFromWhere() {

        fromRegister = getIntent().getBooleanExtra(Constant.FLAG, true);

        if (fromRegister) {
            title("注 册")
                    .titleColor(R.color.black)
                    .titleBarBackground(R.color.defaultWhite)
                    .leftImageRes(R.drawable.ic_arrow_left_black);

            StatusUtils.setStatusBar(this, false, false);
            StatusUtils.setStatusTextColor(true, this);

            mKeyboardHelper = new KeyboardHelper(this, layoutRoot);
            mKeyboardHelper.enable();
        } else {
            title("添加车辆");
            space.setVisibility(View.GONE);
            ivStep.setVisibility(View.GONE);
        }
    }

    @Override
    public void initView() {
        space = findViewById(R.id.space);
        layoutRoot = findViewById(R.id.root);
        btnCommit = findViewById(R.id.btn_commit);
        listView = findViewById(R.id.lv_car_info);
        ivStep = findViewById(R.id.tv_register_step);

        btnCommit.setTag(0);

        sparseArray = new SparseArray<>();
        stringList = Arrays.asList("车牌号:", "准运证号:", "挂车牌号:", "车型:", "出厂年份:", "载重:", "车长:");

    }

    @Override
    public void setListener() {
        super.setListener();
        btnCommit.setOnClickListener(this);

        adapter = new CarInfoAdapter();
        listView.setAdapter(adapter);

    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getTag() == null) {
            return;
        }
        switch ((int) v.getTag()) {
            case -1:
                dialog.disMiss();
                ActivityManager.finishAllActivity();
                startActivity(new Intent(this, MainActivity.class));
                break;
            case 0:
                ImageView imageView = new ImageView(this);
                imageView.setTag(-1);
                imageView.setOnClickListener(this);
                imageView.setImageResource(R.drawable.img_register_alert);
                dialog = new CommonDialog.Builder()
                        .view(imageView)
                        .autoWidth(true)
                        .cancelable(false)
                        .canceledOnTouchOutside(false)
                        .create(this);
                dialog.show();
                break;
            case 1:
                break;
            default:
                break;
        }
    }

    /**
     * 判断是否填入数据
     *
     * @return
     */
    public boolean isEmpty() {
        boolean isEmpty = false;
        for (int i = 0; i < sparseArray.size(); i++) {
            String text = sparseArray.get(i);
            if (TextUtils.isEmpty(text)) {
                isEmpty = true;
            }
        }
        return isEmpty;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mKeyboardHelper != null) {
            mKeyboardHelper.disable();
        }
    }

    class CarInfoAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return stringList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(CompleteCarInfoActivity.this, R.layout.item_car_info, null);
            TextView textView = convertView.findViewById(R.id.tv_item_name);
            ImageView imageView = convertView.findViewById(R.id.iv_item_arrow);
            MyEditText editText = convertView.findViewById(R.id.edit_item_value);

            textView.setText(stringList.get(position));

            if (position == 3 || position == 4 || position == 6) {
                convertView.setTag(position);
                imageView.setVisibility(View.VISIBLE);
                editText.setVisibility(View.INVISIBLE);
                convertView.setOnClickListener(CompleteCarInfoActivity.this);
            }

            editText.addOnTextWatcher(new MyEditText.OnTextWatcher() {
                @Override
                public void onTextChanged(View v, String s) {
                    sparseArray.put(position, s);
                    if (CompleteCarInfoActivity.this.isEmpty()) {
                        btnCommit.setBackgroundResource(R.drawable.shape_login_blue_alpha);
                    } else {
                        btnCommit.setBackgroundResource(R.drawable.shape_login_blue);
                    }
                }
            });
            return convertView;
        }
    }
}
