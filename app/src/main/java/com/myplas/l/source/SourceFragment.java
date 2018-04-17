package com.myplas.l.source;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.huangbryant.hindicator.HIndicatorDialog;
import com.myplas.l.R;
import com.myplas.l.annotation.LoginInspect;
import com.myplas.l.base.BaseFragment;
import com.myplas.l.common.listener.LoadMoreListener;
import com.myplas.l.common.utils.ToastUtil;
import com.huangbrayant.citypicker.CityPicker;
import com.huangbrayant.citypicker.adapter.OnPickListener;
import com.huangbrayant.citypicker.model.City;
import com.huangbrayant.citypicker.model.HotCity;
import com.huangbrayant.citypicker.model.LocateState;
import com.huangbrayant.citypicker.model.LocatedCity;
import com.myplas.l.common.widgets.XPopupWindow;
import com.myplas.l.source.amap.AMapUtil;

import java.util.Arrays;
import java.util.List;

/**
 * @author Huangshuang  2018/3/15 0015
 */

public class SourceFragment extends BaseFragment implements LoadMoreListener.Listener
        , SwipeRefreshLayout.OnRefreshListener
        , View.OnClickListener, XPopupWindow.OnItemClickListener, AMapLocationListener {

    private View divider;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private RelativeLayout rlCarModel, rlCarLength;
    private TextView tvRouteStart, tvRouteEnd, tvCarModel, tvCarLength;

    private SourceAdapter adapter;
    private LoadMoreListener loadMoreListener;

    private List<HotCity> hotCities;
    private int size;

    private View carDialogView;
    private XPopupWindow carModelPop, carLengthPop;

    private List<String> carModelList, carLengthList;

    private AMapLocationClient aMapLocationClient;

    private LocatedCity locatedCity;

    private CityPicker endCityPicker, startCityPicker;


    @Override
    public int getContentView() {
        return R.layout.fragment_source;
    }

    @Override
    public void initView() {
        divider = viewRoot.findViewById(R.id.divider_config);
        tvRouteEnd = viewRoot.findViewById(R.id.tv_route_end);
        tvCarModel = viewRoot.findViewById(R.id.tv_car_model);
        rlCarModel = viewRoot.findViewById(R.id.rl_car_model);
        tvCarLength = viewRoot.findViewById(R.id.tv_car_length);
        rlCarLength = viewRoot.findViewById(R.id.rl_car_length);
        recyclerView = viewRoot.findViewById(R.id.recyclerview);
        refreshLayout = viewRoot.findViewById(R.id.refreshlayout);
        tvRouteStart = viewRoot.findViewById(R.id.tv_route_start);
        carDialogView = View.inflate(activity, R.layout.dialog_popupwindow, null);

        tvRouteStart.setText("上海");
        tvRouteEnd.setText("全国");

        LinearLayoutManager manager = new LinearLayoutManager(activity);
        adapter = new SourceAdapter(activity);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void setListener() {
        super.setListener();
        tvRouteEnd.setOnClickListener(this);
        rlCarModel.setOnClickListener(this);
        rlCarLength.setOnClickListener(this);
        tvRouteStart.setOnClickListener(this);
        refreshLayout.setOnRefreshListener(this);

        loadMoreListener = new LoadMoreListener(this);
        recyclerView.addOnScrollListener(loadMoreListener);
    }

    @Override
    public void onLoadMore() {
        size += 15;
        adapter.setSize(size);
        adapter.notifyDataSetChanged();
        loadMoreListener.onLoadComplete();
        ToastUtil.show(activity, "加载成功！");
    }


    @Override
    public void onRefresh() {
        size = 15;
        adapter.setSize(size);
        adapter.notifyDataSetChanged();
        refreshLayout.setRefreshing(false);
        ToastUtil.show(activity, "刷新成功！");
    }

    @LoginInspect(flag = "123")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_car_model:
                selectCarInfo();
                break;
            case R.id.rl_car_length:
                selectCarLength();
                break;
            case R.id.tv_route_start:
                chooseRouteStart();
                break;
            case R.id.tv_route_end:
                chooseRouteEnd();
                break;
            default:
                break;
        }
    }

    /**
     * 选择车型
     */
    private void selectCarInfo() {
        if (carModelPop == null) {
            carModelList = Arrays.asList("全部"
                    , "高栏"
                    , "平板"
                    , "箱式"
                    , "高地板"
                    , "自卸"
                    , "冷藏"
                    , "危险品"
                    , "其他");
            carModelPop = new XPopupWindow(carDialogView, activity)
                    .setType(1)
                    .title("选择车型")
                    .setList(carModelList)
                    .setListener(this);
            carModelPop.showAsDropDown(divider);
        } else {
            carModelPop.showAsDropDown(divider);
        }
        tvCarModel.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_up_blue, 0);
    }

    /**
     * 选择车长
     */
    private void selectCarLength() {
        if (carLengthPop == null) {
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
            carLengthPop = new XPopupWindow(carDialogView, activity)
                    .setType(2)
                    .title("选择车长")
                    .setList(carLengthList)
                    .setListener(this);
            carLengthPop.showAsDropDown(divider);
        } else {
            carLengthPop.showAsDropDown(divider);
        }
        tvCarLength.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_up_blue, 0);
    }

    /**
     * 选择起点
     */
    private void chooseRouteStart() {
        if (startCityPicker == null) {
            startCityPicker = CityPicker.getInstance()
                    .title("起点选择")
                    .enableAnimation(true)
                    .setFragmentManager(activity.getSupportFragmentManager())
                    .setOnPickListener(new OnPickListener() {
                        @Override
                        public void onPick(int position, City data) {
                            if (data != null) {
                                tvRouteStart.setText(data.getName());
                            }
                        }

                        @Override
                        public void onLocate() {
                            //开始定位
                        }
                    });
            startCityPicker.show();
        } else {
            startCityPicker.show();
        }

//        aMapLocationClient = AMapUtil.initAMapLocation(activity, SourceFragment.this);
//        aMapLocationClient.startLocation();
    }

    /**
     * 选择终点
     */
    private void chooseRouteEnd() {
        if (endCityPicker == null) {
            endCityPicker = CityPicker.getInstance()
                    .title("终点选择")
                    .enableAnimation(true)
                    .setFragmentManager(activity.getSupportFragmentManager())
                    .setOnPickListener(new OnPickListener() {
                        @Override
                        public void onPick(int position, City data) {
                            if (data != null) {
                                tvRouteEnd.setText(data.getName());
                            }
                        }

                        @Override
                        public void onLocate() {

                        }
                    });
            endCityPicker.show();
        } else {
            endCityPicker.show();
        }

//        aMapLocationClient = AMapUtil.initAMapLocation(activity, SourceFragment.this);
//        aMapLocationClient.startLocation();
    }

    @Override
    public void onPopItemClick(int type, int position) {
        if (position != -1) {
            if (type == 1) {
                tvCarModel.setText(carModelList.get(position));
                tvCarModel.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_down_black, 0);
            } else {
                tvCarLength.setText(carLengthList.get(position));
                tvCarLength.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_down_black, 0);
            }
        }
        tvCarModel.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_down_black, 0);
        tvCarLength.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_down_black, 0);
    }


    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        aMapLocationClient.stopLocation();
        if (aMapLocation.getErrorCode() == 0) {
            locatedCity = new LocatedCity(aMapLocation.getCity()
                    , aMapLocation.getProvince()
                    , aMapLocation.getCityCode());
            CityPicker.getInstance().locateComplete(locatedCity, LocateState.SUCCESS);
        }
    }
}
