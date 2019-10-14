package com.example.androiddemo.baidu_map;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.example.androiddemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaiduRouteActivity extends AppCompatActivity {
    @BindView(R.id.mapViewRoute)
    MapView mapViewRoute;
    private BaiduMap baiduMapRoute;
    private LocationClient locationClientRoute;
    private RoutePlanSearch mSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidu_route);
        ButterKnife.bind(this);

        baiduMapRoute=mapViewRoute.getMap();
        initRouteLocation();

        //开启地图定位图层
        locationClientRoute.start();
//        开启定位
        baiduMapRoute.setMyLocationEnabled(true);
    }

    private void initRouteLocation() {
        //定位初始化
        locationClientRoute = new LocationClient(this);

        //通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);

        //设置locationClientOption
        locationClientRoute.setLocOption(option);

        //注册LocationListener监听器
        MyLocationListener myLocationListener = new MyLocationListener();
        locationClientRoute.registerLocationListener(myLocationListener);
    }

    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || mapViewRoute == null){
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            baiduMapRoute.setMyLocationData(locData);

            locationClientRoute.stop();

            //            获得当前坐标
            LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());
            //        设置地图缩放等级
            MapStatus.Builder builder=new MapStatus.Builder();
            builder.target(latLng).zoom(18.0f);
            baiduMapRoute.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

            mSearch = RoutePlanSearch.newInstance();
            OnGetRoutePlanResultListener listener = new OnGetRoutePlanResultListener() {
                @Override
                public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
                    //创建WalkingRouteOverlay实例
                    WalkingRouteOverlay overlay = new WalkingRouteOverlay(baiduMapRoute);
                    if (walkingRouteResult.getRouteLines().size() > 0) {
                        //获取路径规划数据,(以返回的第一条数据为例)
                        //为WalkingRouteOverlay实例设置路径数据
                        overlay.setData(walkingRouteResult.getRouteLines().get(0));
                        //在地图上绘制WalkingRouteOverlay
                        overlay.addToMap();
                    }
                }

                @Override
                public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

                }

                @Override
                public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

                }

                @Override
                public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {

                }

                @Override
                public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

                }

                @Override
                public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

                }
            };
            mSearch.setOnGetRoutePlanResultListener(listener);
            PlanNode stNode = PlanNode.withCityNameAndPlaceName("杭州","西新地铁站");
            PlanNode enNode = PlanNode.withCityNameAndPlaceName("杭州","南星桥地铁站");
            mSearch.walkingSearch((new WalkingRoutePlanOption())
                    .from(stNode)
                    .to(enNode));
            mSearch.destroy();
        }
    }

    @Override
    protected void onResume() {
        mapViewRoute.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mapViewRoute.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapViewRoute.onDestroy();
    }
}
