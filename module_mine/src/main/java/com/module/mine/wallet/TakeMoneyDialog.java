package com.module.mine.wallet;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


import com.module.base.widgets.MyBottomDialog;
import com.module.mine.R;

import java.util.Arrays;
import java.util.List;

/**
 * @author Huangshuang  2018/3/22 0022
 */

public class TakeMoneyDialog extends MyBottomDialog implements AdapterView.OnItemClickListener {

    private TextView tvMoney;
    private ImageView tvClose;
    private TakeOutMoneyActivity activity;
    private GridView gvKeyboard, gvPassword;

    private List<String> list;
    private String[] passwdArray;
    private final static int PASSWORDLENGTH = 6;
    private PassWordAdapter passWordAdapter;

    private String money;

    public TakeMoneyDialog(@NonNull Activity activity) {
        super(activity);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_take_money;
    }


    @Override
    protected void initView() {

        activity = (TakeOutMoneyActivity) super.activity;

        tvClose = findViewById(R.id.dialog_close);
        tvMoney = findViewById(R.id.dialog_money);
        gvKeyboard = findViewById(R.id.dialog_gv_keyboard);
        gvPassword = findViewById(R.id.dialog_gv_password);

        list = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "", "0", "×");
        gvKeyboard.setAdapter(new KeyBoardAdapter());

        passwdArray = new String[PASSWORDLENGTH];
        passWordAdapter = new PassWordAdapter();
        gvPassword.setAdapter(passWordAdapter);
    }

    @Override
    protected void setListener() {
        tvClose.setOnClickListener(this);
        gvKeyboard.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.dialog_close) {
            dismiss();

        } else {
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 9) {
            return;
        }
        //删除
        if (position == list.size() - 1) {
            passWordAdapter.delete();
        }
        //输入
        else if (TextUtils.isEmpty(passwdArray[PASSWORDLENGTH - 1])) {
            passWordAdapter.notifyDataChanged(list.get(position));
        }

    }

    public void setMoney(String money) {
        if (TextUtils.isEmpty(money)) {
            return;
        }
        this.money = money;
        tvMoney.setText("￥" + money);
    }


    /**
     * 自定义键盘对应的adapter
     */
    private class KeyBoardAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(activity).inflate(R.layout.item_keyboard, null);
            }
            TextView textView = convertView.findViewById(R.id.item_keyboard_tv);
            textView.setText(list.get(position));
            return convertView;
        }
    }

    /**
     * 密码框对应的adapter
     */
    private class PassWordAdapter extends BaseAdapter {

        private int target;

        private SparseArray<TextView> tvArray;
        private SparseArray<View> viewArray;

        public PassWordAdapter() {
            tvArray = new SparseArray<>();
            viewArray = new SparseArray<>();
        }

        @Override
        public int getCount() {
            return PASSWORDLENGTH;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (viewArray.get(position) == null) {
                convertView = LayoutInflater.from(activity).inflate(R.layout.item_password, null);

                View divider = convertView.findViewById(R.id.item_password_divider);
                TextView textView = convertView.findViewById(R.id.item_password_tv);

                tvArray.put(position, textView);
                viewArray.put(position, convertView);

                if (position == PASSWORDLENGTH - 1) {
                    divider.setVisibility(View.GONE);
                }
            }
            return viewArray.get(position);
        }

        /**
         * 点击输入密码
         *
         * @param pwd
         */
        public void notifyDataChanged(String pwd) {
            // 以防数组越界
            if (target < 0) {
                target = 0;
            }
            if (target <= PASSWORDLENGTH - 1) {
                tvArray.get(target).setText("●");
                passwdArray[target] = pwd;
                if (target < PASSWORDLENGTH - 1) {
                    target++;
                } else {
                    onCompleted();
                }
            }
        }

        /**
         * 删除
         */
        public void delete() {
            // 以防数组越界
            if (target > PASSWORDLENGTH - 1) {
                target = PASSWORDLENGTH - 1;
            }
            if (target >= 0) {
                if (target > 0) {
                    target--;
                }
                tvArray.get(target).setText(null);
                passwdArray[target] = null;
            }
        }
    }

    /**
     * 输入完成
     */
    private void onCompleted() {
        dismiss();
    }
}
