package com.example.androiddemo.baidu_map;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.androiddemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoOnActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.goonEdit1)
    EditText goonEdit1;
    @BindView(R.id.goonEdit2)
    EditText goonEdit2;
    @BindView(R.id.goonMap)
    MapView goonMap;
    @BindView(R.id.goonSearch)
    Button goonSearch;
    private BaiduMap baiduMap;
    private String editText1;
    private String editText2;
    private LocationClient goOnLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_on);
        ButterKnife.bind(this);
        editText1= String.valueOf(goonEdit1.getText());
        editText2= String.valueOf(goonEdit2.getText());
        baiduMap=goonMap.getMap();
        initGoOnMap();
        initGoOnLocation();

        //开启地图定位图层
        goOnLocationClient.start();
//        开启定位
        baiduMap.setMyLocationEnabled(true);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.goonSearch:
                break;
        }
    }

    private void initGoOnMap() {
//        设置地图为普通地图
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
//        开启交通图
        baiduMap.setTrafficEnabled(true);
//          自定义定位内容(圈周边的颜色，圈图标等)
//        MyLocationConfiguration有2个构造方法:
//        new MyLocationConfiguration(LocationMode lm, boolean direction, BitmapDescriptor bd);
//        new MyLocationConfiguration(LocationMode lm, boolean direction, BitmapDescriptor bd,int fillColor,int strokeColor);
//        MyLocationConfiguration myLocationConfiguration=new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL,false, BitmapDescriptorFactory.fromResource(R.drawable.icon_start));
//        baiduMap.setMyLocationConfiguration(myLocationConfiguration);
    }

    private void initGoOnLocation() {
        //定位初始化
        goOnLocationClient = new LocationClient(this);

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
        goOnLocationClient.setLocOption(option);

        //注册LocationListener监听器
        MyLocationListener myLocationListener = new MyLocationListener();
        goOnLocationClient.registerLocationListener(myLocationListener);
    }

    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || baiduMap == null) {
                return;
            }
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(latitude)
                    .longitude(longitude).build();
            baiduMap.setMyLocationData(locData);
            goOnLocationClient.stop();

            //            获得当前坐标
            LatLng latLng = new LatLng(latitude, longitude);
            //        设置地图缩放等级
            MapStatus.Builder builder = new MapStatus.Builder();
            builder.target(latLng).zoom(18.0f);
            baiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        }
    }
}
