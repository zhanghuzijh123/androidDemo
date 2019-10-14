package com.example.androiddemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;


public class MyService extends Service {
    private static final String msg="MyService";
    private DownloadBinder binder=new DownloadBinder();

    public MyService() {
    }

    class DownloadBinder extends Binder{
        public void startDownload(){
            Log.d(msg, "startDownload: executed");
        }

        public int getProgress(){
            Log.d(msg, "getProgress: executed");
            return 0;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        return binder;
    }

//    在服务创建的时候调用
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(msg, "onCreate: ");

//        Intent intent=new Intent(this,ServiceActivity.class);
//        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,0);
//        Notification notification=new Notification.Builder(this)
//                .setContentTitle("this is content title")
//                .setContentText("this is content text")
//                .setWhen(System.currentTimeMillis())
//                .setContentIntent(pendingIntent)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
//                .build();
//        startForeground(1,notification);
    }

//    在每次服务启动的时候调用
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(msg, "onStartCommand: ");
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                stopSelf();
//            }
//        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

//    在服务销毁的时候调用
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(msg, "onDestroy: ");
    }
}
