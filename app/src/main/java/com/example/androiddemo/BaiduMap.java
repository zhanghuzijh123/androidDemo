package com.example.androiddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.androiddemo.baidu_map.BaiduDemoActivity;
import com.example.androiddemo.baidu_map.BaiduRouteActivity;
import com.example.androiddemo.baidu_map.GoOnActivity;
import com.example.androiddemo.baidu_map.POIActivity;
import com.example.androiddemo.baidu_map.ShowBaiduMap;
import com.example.androiddemo.baidu_map.WalkOnActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaiduMap extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.map_btn1)
    Button map_btn1;
    @BindView(R.id.map_btn2)
    Button map_btn2;
    @BindView(R.id.map_btn3)
    Button map_btn3;
    @BindView(R.id.map_btn4)
    Button map_btn4;
    @BindView(R.id.map_btn5)
    Button map_btn5;
    @BindView(R.id.map_btn6)
    Button map_btn6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidu_map);

        ButterKnife.bind(this);
        map_btn1.setOnClickListener(this);
        map_btn2.setOnClickListener(this);
        map_btn3.setOnClickListener(this);
        map_btn4.setOnClickListener(this);
        map_btn5.setOnClickListener(this);
        map_btn6.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.map_btn1:
                startActivity(new Intent(this, ShowBaiduMap.class));
                break;

            case R.id.map_btn2:
                startActivity(new Intent(this, POIActivity.class));
                break;

            case R.id.map_btn3:
                startActivity(new Intent(this, BaiduRouteActivity.class));
                break;

            case R.id.map_btn4:
                startActivity(new Intent(this, WalkOnActivity.class));
                break;

            case R.id.map_btn5:
                startActivity(new Intent(this, BaiduDemoActivity.class));
                break;

            case R.id.map_btn6:
                startActivity(new Intent(this, GoOnActivity.class));
                break;
        }
    }
}
