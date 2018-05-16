package com.module.mine.personinfo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.module.mine.R;

import java.util.Arrays;
import java.util.List;

/**
 * @author Huangshuang  2018/3/19 0019
 */

public class MyInfoAdapter extends BaseAdapter {

    private Context context;
    private List<String> text;

    public MyInfoAdapter(Context context) {
        this.context = context;
        initList();
    }

    @Override
    public int getCount() {
        return text.size();
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

        return initView(position);
    }

    /**
     * 初始化Item 并设置数据
     *
     * @param position
     */
    private View initView(int position) {
        View convertView = View.inflate(context, R.layout.item_mine_myinfo, null);

        TextView tvType = convertView.findViewById(R.id.myinfo_tv_type);
        TextView tvValue = convertView.findViewById(R.id.myinfo_tv_value);
        ImageView imageView = convertView.findViewById(R.id.myinfo_iv);

        tvType.setText(text.get(position));
        switch (position) {
            case 0:
                imageView.setImageResource(R.drawable.img_default_user);
                break;
            case 1:
                tvValue.setText("15378412400");
                break;
            case 2:
                tvValue.setText("张碧晨");
                break;
            case 3:
                tvValue.setText("51082119920707xxxx");
                break;
            case 4:
                imageView.setImageResource(R.drawable.ic_card);
                break;
            default:
                break;
        }

        return convertView;
    }

    /**
     * 初始化数据
     */
    private void initList() {
        text = Arrays.asList("头像"
                , "手机号码"
                , "姓名"
                , "身份证号码"
                , "驾驶证");
    }
}
