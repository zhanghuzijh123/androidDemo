package com.example.androiddemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ServiceLive extends Service {
//    public static final String TAG="ServiceLive";等同于以下语句
    public static final String TAG=ServiceLive.class.getName();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return new InnerBinder();
    }

    private class InnerBinder extends Binder implements IService{
        @Override
        public void callServiceInnerMethod() {
            Log.d(TAG, "callServiceInnerMethod: ");
            sayHello();
        }
    }

    private void sayHello() {
        Log.d(TAG, "sayHello: ");
        Toast.makeText(this,"调用服务内部方法",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    //    onStartCommand方法已经替代原onStart方法
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
