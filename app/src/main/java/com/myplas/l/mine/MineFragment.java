package com.myplas.l.mine;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myplas.l.R;
import com.myplas.l.base.BaseFragment;
import com.myplas.l.base.Constant;
import com.myplas.l.common.utils.SPUtil;
import com.myplas.l.common.widgets.RoundImageView;
import com.myplas.l.common.widgets.XListView;
import com.myplas.l.login.CompletePersonInfoActivity;
import com.myplas.l.mine.advancepayment.AdvancePaymentActivity;
import com.myplas.l.mine.car.CarManageActivity;
import com.myplas.l.mine.personinfo.MyInfoActivity;
import com.myplas.l.mine.route.RouteManageActivity;
import com.myplas.l.mine.setting.SettingActivity;
import com.myplas.l.mine.wallet.MyAssetsActivity;
import com.myplas.l.mine.waybill.WaybillActivity;

/**
 * @author Huangshuang  2018/3/15 0015
 */

public class MineFragment extends BaseFragment implements View.OnClickListener
        , AdapterView.OnItemClickListener {

    private XListView listView;
    private RoundImageView ivHead;
    private ImageView ivBack, ivImage;
    private TextView tvTltle, tvName, tvPhone, tvCertify, tvContact;
    private LinearLayout layoutInfo, layoutCertified, layoutUnCertified;

    @Override
    public int getContentView() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView() {
        listView = viewRoot.findViewById(R.id.mine_lv);
        ivHead = viewRoot.findViewById(R.id.mine_iv_head);
        ivImage = viewRoot.findViewById(R.id.mine_iv_img);
        tvName = viewRoot.findViewById(R.id.mine_tv_name);
        ivBack = viewRoot.findViewById(R.id.titleBar_left_img);
        tvPhone = viewRoot.findViewById(R.id.mine_tv_phone);
        tvTltle = viewRoot.findViewById(R.id.titleBar_title);
        layoutInfo = viewRoot.findViewById(R.id.mine_ll_info);
        tvCertify = viewRoot.findViewById(R.id.mine_tv_certify);
        tvContact = viewRoot.findViewById(R.id.mine_tv_contact);
        layoutCertified = viewRoot.findViewById(R.id.mine_ll_certified);
        layoutUnCertified = viewRoot.findViewById(R.id.mine_ll_uncertified);

        tvTltle.setText("我 的");
        ivBack.setVisibility(View.GONE);

        listView.setAdapter(new MineAdapter(activity));

//        layoutCertified.setVisibility(View.GONE);
//        layoutUnCertified.setVisibility(View.VISIBLE);

    }

    @Override
    public void setListener() {
        super.setListener();

        ivImage.setOnClickListener(this);
        tvCertify.setOnClickListener(this);
        tvContact.setOnClickListener(this);
        layoutInfo.setOnClickListener(this);

        listView.setOnItemClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.mine_tv_certify:
                startActivity(new Intent(activity, CompletePersonInfoActivity.class));
                break;
            case R.id.mine_tv_contact:
                break;
            case R.id.mine_ll_info:
                startActivity(new Intent(activity, MyInfoActivity.class));
                break;
            case R.id.mine_iv_img:
                startActivity(new Intent(activity, AdvancePaymentActivity.class));
                break;
            default:
                break;

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(new Intent(activity, WaybillActivity.class));
                break;
            case 1:
                startActivity(new Intent(activity, MyAssetsActivity.class));
                break;
            case 2:
                startActivity(new Intent(activity, CarManageActivity.class));
                break;
            case 3:
                startActivity(new Intent(activity, RouteManageActivity.class));
                break;
            case 4:

                break;
            case 5:
                startActivity(new Intent(activity, SettingActivity.class));
                break;
            default:
                break;
        }
    }
}
