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
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiBoundSearchOption;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.example.androiddemo.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class POIActivity extends AppCompatActivity {
    @BindView(R.id.poiMap)
    MapView poiMap;
    private BaiduMap poiBaiduMap;
    private LocationClient locationClient;
    private PoiSearch mPoiSearch;
    private PoiResult poiResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poi);
        ButterKnife.bind(this);

        poiBaiduMap=poiMap.getMap();
        initPOILocation();
        initPOI();
        locationClient.start();
        poiBaiduMap.setMyLocationEnabled(true);
    }

    private void initPOI() {
        mPoiSearch = PoiSearch.newInstance();
        OnGetPoiSearchResultListener listener = new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {
                    poiBaiduMap.clear();

                    //创建PoiOverlay对象
                    PoiOverlay poiOverlay = new PoiOverlay(poiBaiduMap);

                    //设置Poi检索数据
                    poiOverlay.setData(poiResult);

                    //将poiOverlay添加至地图并缩放至合适级别
                    poiOverlay.addToMap();
                    poiOverlay.zoomToSpan();
                }
            }
            @Override
            public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {
                //PoiInfo 检索到的第一条信息
                PoiInfo poi = poiResult.getAllPoi().get(0);
                //通过第一条检索信息对应的uid发起详细信息检索
                mPoiSearch.searchPoiDetail((new PoiDetailSearchOption())
                        .poiUids(poi.uid)); // uid的集合，最多可以传入10个uid，多个uid之间用英文逗号分隔。
            }
            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

            }
            //废弃
            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

            }
        };
        mPoiSearch.setOnGetPoiSearchResultListener(listener);
//        mPoiSearch.searchNearby(new PoiNearbySearchOption().location(new LatLng(30.228952,120.181168)).radius(10000).keyword("杭州银行").pageNum(10));
        mPoiSearch.searchInCity(new PoiCitySearchOption().city("杭州").keyword("杭州银行").pageNum(10));
        mPoiSearch.destroy();
    }

    private void initPOILocation() {
        //定位初始化
        locationClient = new LocationClient(this);

        //通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);

        //设置locationClientOption
        locationClient.setLocOption(option);

        //注册LocationListener监听器
        MyLocationListener myLocationListener = new MyLocationListener();
        locationClient.registerLocationListener(myLocationListener);
    }

    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || poiMap == null){
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            poiBaiduMap.setMyLocationData(locData);
            locationClient.stop();

            //            获得当前坐标
            LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());
            //        设置地图缩放等级
            MapStatus.Builder builder=new MapStatus.Builder();
            builder.target(latLng).zoom(18.0f);
            poiBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        }
    }

    @Override
    protected void onResume() {
        poiMap.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        poiMap.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        poiMap.onDestroy();
    }
}
