package com.example.androiddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.androiddemo.service.IService;
import com.example.androiddemo.service.ServiceLive;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ServiceNewActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.serviceBtn1)
    Button serviceBtn1;
    @BindView(R.id.serviceBtn2)
    Button serviceBtn2;
    @BindView(R.id.serviceBtn3)
    Button serviceBtn3;
    @BindView(R.id.serviceBtn4)
    Button serviceBtn4;
    @BindView(R.id.serviceBtn5)
    Button serviceBtn5;
    private static final String TAG=ServiceNewActivity.class.getName();
    private IService innerBinder;
    private boolean inner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_new);

        ButterKnife.bind(this);
        serviceBtn1.setOnClickListener(this);
        serviceBtn2.setOnClickListener(this);
        serviceBtn3.setOnClickListener(this);
        serviceBtn4.setOnClickListener(this);
        serviceBtn5.setOnClickListener(this);
    }

    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "onServiceConnected: ");
            innerBinder=(IService) iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected: ");
            innerBinder=null;
        }
    };

//    关于开始/结束服务与绑定/解除服务之间的差别：startService可以长期在后台运行，bindService则不行；但是bindService则可以跟服务进行通讯，startService则不行
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.serviceBtn1:
                startService(new Intent(this,ServiceLive.class));
                break;

            case R.id.serviceBtn2:
                stopService(new Intent(this,ServiceLive.class));
                break;

            case R.id.serviceBtn3:
//               绑定服务
                Log.d(TAG, "绑定服务");
                inner=bindService(new Intent(this,ServiceLive.class),serviceConnection,BIND_AUTO_CREATE);
                break;

            case R.id.serviceBtn4:
//                解除绑定
                if (serviceConnection!=null&&inner){
                    Log.d(TAG, "解除绑定");
                    unbindService(serviceConnection);
                }
                break;

            case R.id.serviceBtn5:
//                调用服务内部方法
                Log.d(TAG, "调用服务内部方法");
                innerBinder.callServiceInnerMethod();
                break;
        }
    }
}
