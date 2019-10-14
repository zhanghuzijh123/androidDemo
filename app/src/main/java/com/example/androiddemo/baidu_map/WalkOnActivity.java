package com.example.androiddemo.baidu_map;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

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
import com.baidu.mapapi.walknavi.WalkNavigateHelper;
import com.baidu.mapapi.walknavi.adapter.IWEngineInitListener;
import com.baidu.mapapi.walknavi.adapter.IWRoutePlanListener;
import com.baidu.mapapi.walknavi.model.WalkRoutePlanError;
import com.baidu.mapapi.walknavi.params.WalkNaviLaunchParam;
import com.example.androiddemo.R;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WalkOnActivity extends AppCompatActivity {
    @BindView(R.id.walkOnMap)
    MapView mapViewWalk;
    private BaiduMap baiduMapWalk;
    private LocationClient locationClientWalk;
    private LatLng startPt;
    private LatLng endPt;
    private WalkNaviLaunchParam mParam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk_on);
        ButterKnife.bind(this);
        baiduMapWalk=mapViewWalk.getMap();
        initWalkLocation();
        initWalk();

        //开启地图定位图层
        locationClientWalk.start();
//        开启定位
        baiduMapWalk.setMyLocationEnabled(true);
    }

    private void initWalk() {
            startPt = new LatLng(30.228952,120.181168);
            endPt = new LatLng(30.238952,120.191168);
            // 获取导航控制类
            // 引擎初始化
            WalkNavigateHelper.getInstance().initNaviEngine(WalkOnActivity.this, new IWEngineInitListener() {

                @Override
                public void engineInitSuccess() {
                    //引擎初始化成功的回调
                    routeWalkPlanWithParam(startPt,endPt);
                }

                @Override
                public void engineInitFail() {
                    //引擎初始化失败的回调
                }
            });
    }

    private void initWalkLocation() {
        //定位初始化
        locationClientWalk = new LocationClient(this);

        //通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        //可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
        option.setCoorType("bd09ll"); // 设置坐标类型
        //可选，默认0，即仅定位一次，设置发起连续定位请求的间隔需要大于等于1000ms才是有效的
        option.setScanSpan(1000);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);

        //设置locationClientOption
        locationClientWalk.setLocOption(option);

        //注册LocationListener监听器
        MyLocationListener myLocationListener = new MyLocationListener();
        locationClientWalk.registerLocationListener(myLocationListener);
    }

    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || mapViewWalk == null){
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            baiduMapWalk.setMyLocationData(locData);
            locationClientWalk.stop();

            //            获得当前坐标
            LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());
            //        设置地图缩放等级
            MapStatus.Builder builder=new MapStatus.Builder();
            builder.target(latLng).zoom(18.0f);
            baiduMapWalk.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

//            startPt = new LatLng(location.getLatitude(),location.getLongitude());
//            endPt = new LatLng(location.getLatitude()+0.01,location.getLongitude()+0.01);
//            // 获取导航控制类
//            // 引擎初始化
//            WalkNavigateHelper.getInstance().initNaviEngine(WalkOnActivity.this, new IWEngineInitListener() {
//
//                @Override
//                public void engineInitSuccess() {
//                    //引擎初始化成功的回调
//                    routeWalkPlanWithParam(startPt,endPt);
//                }
//
//                @Override
//                public void engineInitFail() {
//                    //引擎初始化失败的回调
//                }
//            });
        }
    }

    public void routeWalkPlanWithParam(LatLng start,LatLng end){
        mParam=new WalkNaviLaunchParam().stPt(start).endPt(end);
        //发起算路
        WalkNavigateHelper.getInstance().routePlanWithParams(mParam, new IWRoutePlanListener() {
            @Override
            public void onRoutePlanStart() {
                //开始算路的回调
            }

            @Override
            public void onRoutePlanSuccess() {
                //算路成功
                //跳转至诱导页面
                Intent intent = new Intent(WalkOnActivity.this, WNaviGuideActivity.class);
                startActivity(intent);
            }

            @Override
            public void onRoutePlanFail(WalkRoutePlanError walkRoutePlanError) {
                //算路失败的回调
            }
        });
    }
}
