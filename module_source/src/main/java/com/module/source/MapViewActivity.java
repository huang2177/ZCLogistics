package com.module.source;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.module.base.base.BaseActivity;
import com.module.base.base.BasePresenter;
import com.module.base.utils.ToastUtil;
import com.module.source.R;
import com.module.source.amap.AMapUtil;
import com.module.source.amap.DrivingRouteOverlay;

/**
 * @author Huangshuang  2018/4/2 0002
 */

public class MapViewActivity extends BaseActivity implements RouteSearch.OnRouteSearchListener {

    private final int ROUTE_TYPE_DRIVE = 2;
    private MapView mapView;
    private RouteSearch routeSearch;
    private LatLonPoint mStartPoint = new LatLonPoint(39.942295, 116.335891);//起点，39.942295,116.335891
    private LatLonPoint mEndPoint = new LatLonPoint(39.995576, 116.481288);//终点，39.995576,116.481288
    private DriveRouteResult mDriveRouteResult;
    private AMap aMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapview);

        title("路线");

        initMapView(savedInstanceState);

    }

    /**
     * 初始化MapView
     *
     * @param savedInstanceState
     */
    private void initMapView(@Nullable Bundle savedInstanceState) {
        mapView.onCreate(savedInstanceState);
        aMap = mapView.getMap();
        setfromandtoMarker();

//        LatLng latLng = new LatLng(45.71701, 126.64256);
//        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11));

        routeSearch = new RouteSearch(this);
        routeSearch.setRouteSearchListener(this);

        RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(mStartPoint, mEndPoint);

        RouteSearch.DriveRouteQuery query = new RouteSearch.DriveRouteQuery(fromAndTo
                , RouteSearch.DrivingDefault
                , null
                , null
                , "");

        routeSearch.calculateDriveRouteAsyn(query);
    }

    private void setfromandtoMarker() {
        aMap.addMarker(new MarkerOptions()
                .position(AMapUtil.convertToLatLng(mStartPoint))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.start)));
        aMap.addMarker(new MarkerOptions()
                .position(AMapUtil.convertToLatLng(mEndPoint))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.end)));
    }

    @Override
    public void initView() {

        mapView = findViewById(R.id.mapview);
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult result, int errorCode) {
        aMap.clear();
        if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getPaths() != null) {
                if (result.getPaths().size() > 0) {
                    mDriveRouteResult = result;
                    final DrivePath drivePath = mDriveRouteResult.getPaths()
                            .get(0);
                    if (drivePath == null) {
                        return;
                    }
                    DrivingRouteOverlay drivingRouteOverlay = new DrivingRouteOverlay(
                            this, aMap, drivePath,
                            mDriveRouteResult.getStartPos(),
                            mDriveRouteResult.getTargetPos(), null);
                    //设置节点marker是否显示
                    drivingRouteOverlay.setNodeIconVisibility(false);
                    //是否用颜色展示交通拥堵情况，默认true
                    drivingRouteOverlay.setIsColorfulline(false);
                    drivingRouteOverlay.removeFromMap();
                    drivingRouteOverlay.addToMap();
                    drivingRouteOverlay.zoomToSpan();

                } else if (result != null && result.getPaths() == null) {
                    ToastUtil.show(this, "没有相关路线");
                }

            } else {
                ToastUtil.show(this, "没有相关路线");
            }
        }
    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {

    }

    @Override
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
}
