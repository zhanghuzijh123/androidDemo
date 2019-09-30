package com.example.androiddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.startService)
    Button start_Service;
    @BindView(R.id.stopService)
    Button stop_Service;
    @BindView(R.id.bindService)
    Button bind_Service;
    @BindView(R.id.unbindService)
    Button unbind_Service;
    private MyService.DownloadBinder downloadBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        ButterKnife.bind(this);

        start_Service.setOnClickListener(this);
        stop_Service.setOnClickListener(this);
        bind_Service.setOnClickListener(this);
        unbind_Service.setOnClickListener(this);
    }

    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            downloadBinder= (MyService.DownloadBinder) iBinder;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.startService:
                Intent startIntent=new Intent(this,MyService.class);
//                开始服务
                startService(startIntent);
                break;

            case R.id.stopService:
                Intent stopIntent=new Intent(this,MyService.class);
//                停止服务
                stopService(stopIntent);
                break;

            case R.id.bindService:
                Intent bindIntent=new Intent(this,MyService.class);
                bindService(bindIntent,connection,BIND_AUTO_CREATE);
                break;

            case R.id.unbindService:
                unbindService(connection);
                break;
        }
    }
}

