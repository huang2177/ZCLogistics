package com.myplas.l.message;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.myplas.l.R;
import com.myplas.l.base.BaseFragment;
import com.myplas.l.base.Constant;

/**
 * @author Huangshuang  2018/3/15 0015
 */

public class MessageFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private ImageView ivBack;
    private TextView tvTltle;
    private ListView lvMessage;

    private MessageAdapter adapter;

    @Override
    public int getContentView() {
        return R.layout.fragment_message;
    }

    @Override
    public void initView() {
        lvMessage = viewRoot.findViewById(R.id.message_lv);
        ivBack = viewRoot.findViewById(R.id.titleBar_left_img);
        tvTltle = viewRoot.findViewById(R.id.titleBar_title);

        tvTltle.setText("消 息");
        ivBack.setVisibility(View.GONE);

        adapter = new MessageAdapter(activity);
        lvMessage.setAdapter(adapter);
    }

    @Override
    public void setListener() {
        super.setListener();
        lvMessage.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(activity, MessageDetailActivity.class);

        switch (position) {
            case 0:
                intent.putExtra(Constant.POSITION, "0");
                intent.putExtra(Constant.TITLE, "车辆预约通知");
                break;
            case 1:
                intent.putExtra(Constant.POSITION, "1");
                intent.putExtra(Constant.TITLE, "系统通知");
                break;
            case 2:
                intent.putExtra(Constant.POSITION, "2");
                intent.putExtra(Constant.TITLE, "活动通知");
                break;
            case 3:
                intent.putExtra(Constant.POSITION, "3");
                intent.putExtra(Constant.TITLE, "成交通知");
                break;
            default:
                break;
        }
        startActivity(intent);
    }
}
