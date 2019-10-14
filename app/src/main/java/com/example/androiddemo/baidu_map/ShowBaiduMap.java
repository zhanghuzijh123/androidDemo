package com.example.androiddemo.baidu_map;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Message;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.LogoPosition;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.androiddemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.baidu.mapapi.map.MyLocationConfiguration.*;

public class ShowBaiduMap extends AppCompatActivity {
    @BindView(R.id.bmapView)
    MapView bmapView;
    private BaiduMap baiduMap;
    private LocationClient mLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_baidu_map);
        ButterKnife.bind(this);

        baiduMap=bmapView.getMap();
        initMap();
        initLocation();

        //开启地图定位图层
        mLocationClient.start();
//        开启定位
        baiduMap.setMyLocationEnabled(true);
    }

    private void initLocation() {
        //定位初始化
        mLocationClient = new LocationClient(this);

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
        mLocationClient.setLocOption(option);

        //注册LocationListener监听器
        MyLocationListener myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);
    }

    private void initMap() {
//        定义BaiduMapOptions对象
//        BaiduMapOptions options = new BaiduMapOptions();
//        设置地图为默认地图
//        options.mapType(BaiduMap.MAP_TYPE_NORMAL);
//        创建MapView对象
//        MapView mapView=new MapView(this,options);
//        添加mapview对象
//        setContentView(mapView);
//        设置地图为普通地图
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
//        开启交通图
        baiduMap.setTrafficEnabled(true);
//        baiduMap.setCustomTrafficColor(String severeCongestion,String congestion,String slow,String smooth);
//        String severeCongestion,String congestion,String slow,String smooth 分别代表严重拥堵，拥堵，缓行，畅通
//        baiduMap.setCustomTrafficColor("#ffba0101", "#fff33131", "#ffff9e19", "#00000000");
        //开启热力图
//        baiduMap.setBaiduHeatMapEnabled(true);

//          自定义定位内容(圈周边的颜色，圈图标等)
//        MyLocationConfiguration有2个构造方法:
//        new MyLocationConfiguration(LocationMode lm, boolean direction, BitmapDescriptor bd);
//        new MyLocationConfiguration(LocationMode lm, boolean direction, BitmapDescriptor bd,int fillColor,int strokeColor);
        MyLocationConfiguration myLocationConfiguration=new MyLocationConfiguration(LocationMode.NORMAL,false, BitmapDescriptorFactory.fromResource(R.drawable.icon_start));
        baiduMap.setMyLocationConfiguration(myLocationConfiguration);

//        设置logo显示的位置
//        bmapView.setLogoPosition(LogoPosition.logoPostionleftTop);
    }

    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || baiduMap == null){
                return;
            }
            double latitude=location.getLatitude();
            double longitude=location.getLongitude();
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(latitude)
                    .longitude(longitude).build();
            baiduMap.setMyLocationData(locData);
            mLocationClient.stop();

            //            获得当前坐标
            LatLng latLng=new LatLng(latitude,longitude);
            //        设置地图缩放等级
            MapStatus.Builder builder=new MapStatus.Builder();
            builder.target(latLng).zoom(18.0f);
            baiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

//            设置单个标记
//            LatLng point=new LatLng(location.getLatitude()+0.01,location.getLongitude()+0.01);
//            BitmapDescriptor bitmap=BitmapDescriptorFactory.fromResource(R.drawable.icon_end);
//            OverlayOptions option=new MarkerOptions()
//                    .position(point)
//                    .icon(bitmap);
//            baiduMap.addOverlay(option);

            List<OverlayOptions> options=new ArrayList<OverlayOptions>();
            options.add(addpoint(latitude+0.01,longitude+0.01,R.drawable.icon_end,1));
            options.add(addpoint(latitude+0.02,longitude+0.02,R.drawable.icon_end,2));
            options.add(addpoint(latitude-0.01,longitude-0.01,R.drawable.icon_end,3));
            baiduMap.addOverlays(options);

//            设置标记事件
            baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    Bundle bundle=new Bundle();
                    Intent intent=new Intent(ShowBaiduMap.this,MessageActivity.class);
                    bundle.putString("location",marker.getPosition().latitude+","+marker.getPosition().longitude);
                    bundle.putInt("id",marker.getExtraInfo().getInt("id"));
                    intent.putExtras(bundle);
                    startActivity(intent);
                    return false;
                }
            });
        }
    }

    private OverlayOptions addpoint(double latitude, double longitude, int icon, int id) {
        Bundle bundle=new Bundle();
        bundle.putInt("id",id);
        BitmapDescriptor bitmapDescriptor=BitmapDescriptorFactory.fromResource(icon);
        OverlayOptions options=new MarkerOptions()
                .icon(bitmapDescriptor)
                .position(new LatLng(latitude,longitude))
                .extraInfo(bundle);
        return options;
    }


    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        bmapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        bmapView.onPause();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        bmapView.onDestroy();
    }
}
