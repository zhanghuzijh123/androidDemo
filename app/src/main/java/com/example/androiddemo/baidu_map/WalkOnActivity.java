package com.example.androiddemo.baidu_map;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.walknavi.WalkNavigateHelper;
import com.baidu.mapapi.walknavi.adapter.IWEngineInitListener;
import com.baidu.mapapi.walknavi.adapter.IWRoutePlanListener;
import com.baidu.mapapi.walknavi.model.WalkRoutePlanError;
import com.baidu.mapapi.walknavi.params.WalkNaviLaunchParam;
import com.baidu.mapapi.walknavi.params.WalkRouteNodeInfo;
import com.example.androiddemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WalkOnActivity extends AppCompatActivity {
    @BindView(R.id.walkOnMap)
    MapView mapViewWalk;
    private BaiduMap baiduMapWalk;
    private LocationClient locationClientWalk;
    private double latitude;
    private double longitude;
    private LatLng startPt;
    private LatLng endPt;
    private WalkNaviLaunchParam mParam;
    private static boolean isPermissionRequested = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk_on);
        ButterKnife.bind(this);
        requestPermission();
        baiduMapWalk=mapViewWalk.getMap();
        initWalkLocation();

        //开启地图定位图层
        locationClientWalk.start();
        //        开启定位
        baiduMapWalk.setMyLocationEnabled(true);
    }

//    开始步行导航
    private void initWalk() {
        try {
            // 获取导航控制类
            // 引擎初始化
            WalkNavigateHelper.getInstance().initNaviEngine(this, new IWEngineInitListener() {
                @Override
                public void engineInitSuccess() {
                    //引擎初始化成功的回调
                    routeWalkPlanWithParam();
                }
                @Override
                public void engineInitFail() {
                    //引擎初始化失败的回调
                    WalkNavigateHelper.getInstance().unInitNaviEngine();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void routeWalkPlanWithParam(){
        //发起算路
        WalkNavigateHelper.getInstance().routePlanWithRouteNode(mParam, new IWRoutePlanListener() {
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

            latitude=location.getLatitude();
            longitude=location.getLongitude();
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection()).latitude(latitude)
                    .longitude(longitude).build();
            baiduMapWalk.setMyLocationData(locData);
            locationClientWalk.stop();

            //            获得当前坐标
            LatLng latLng=new LatLng(latitude,longitude);
            //        设置地图缩放等级
            MapStatus.Builder builder=new MapStatus.Builder();
            builder.target(latLng).zoom(16.0f);
            baiduMapWalk.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

            startPt = new LatLng(latitude,longitude);
//            endPt = new LatLng(latitude+0.01,longitude+0.01);
//            WalkRouteNodeInfo walkStartNode = new WalkRouteNodeInfo();
//            walkStartNode.setLocation(startPt);
//            WalkRouteNodeInfo walkEndNode = new WalkRouteNodeInfo();
//            walkEndNode.setLocation(endPt);
//            mParam = new WalkNaviLaunchParam().startNodeInfo(walkStartNode).endNodeInfo(walkEndNode);

            List<OverlayOptions> options=new ArrayList<OverlayOptions>();
            options.add(addPoint(latitude+0.01,longitude+0.01,R.drawable.icon_end,1));
            options.add(addPoint(latitude+0.02,longitude+0.02,R.drawable.icon_end,1));
            options.add(addPoint(latitude-0.01,longitude-0.01,R.drawable.icon_end,1));
            baiduMapWalk.addOverlays(options);

            baiduMapWalk.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(WalkOnActivity.this);
                    builder.setTitle("请确认");
                    builder.setMessage("是否对此位置进行导航？");
                    builder.setPositiveButton("进行导航", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            endPt=new LatLng(marker.getPosition().latitude,marker.getPosition().longitude);
                            WalkRouteNodeInfo walkStartNode=new WalkRouteNodeInfo();
                            walkStartNode.setLocation(startPt);
                            WalkRouteNodeInfo walkEndNode=new WalkRouteNodeInfo();
                            walkEndNode.setLocation(endPt);
                            mParam=new WalkNaviLaunchParam().startNodeInfo(walkStartNode).endNodeInfo(walkEndNode);

                            mParam.extraNaviMode(0);
                            initWalk();
                        }
                    });

                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.show();
                    return false;
                }
            });
        }
    }

    private OverlayOptions addPoint(double x, double y, int icon, int id) {
        Bundle bundle=new Bundle();
        bundle.putInt("id",id);
        BitmapDescriptor bitmapDescriptor= BitmapDescriptorFactory.fromResource(icon);
        OverlayOptions options=new MarkerOptions()
                .icon(bitmapDescriptor)
                .position(new LatLng(x,y))
                .extraInfo(bundle);
        return options;
    }

    /**
     * Android6.0之后需要动态申请权限
     */
    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= 23 && !isPermissionRequested) {

            isPermissionRequested = true;

            ArrayList<String> permissionsList = new ArrayList<>();

            String[] permissions = {
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.INTERNET,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.MODIFY_AUDIO_SETTINGS,
                    Manifest.permission.WRITE_SETTINGS,
                    Manifest.permission.ACCESS_WIFI_STATE,
                    Manifest.permission.CHANGE_WIFI_STATE,
                    Manifest.permission.CHANGE_WIFI_MULTICAST_STATE


            };

            for (String perm : permissions) {
                if (PackageManager.PERMISSION_GRANTED != checkSelfPermission(perm)) {
                    permissionsList.add(perm);
                    // 进入到这里代表没有权限.
                }
            }

            if (permissionsList.isEmpty()) {
                return;
            } else {
                requestPermissions(permissionsList.toArray(new String[permissionsList.size()]), 0);
            }
        }
    }

    protected void onPause() {
        super.onPause();
        mapViewWalk.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapViewWalk.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapViewWalk.onDestroy();
    }
}
