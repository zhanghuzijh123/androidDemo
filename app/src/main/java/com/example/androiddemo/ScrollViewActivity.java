package com.example.androiddemo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class ScrollViewActivity extends AppCompatActivity {
    static final int NOTIFICATION_ID=0x123;
    NotificationManager nm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);
        nm= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    public void send(View source){
//        Intent intent=new Intent(this,MainActivity.class);
//        PendingIntent pendingIntent=new Notification.Builder(this)
//        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,0);
//        Notification notification=new Notification.Builder(this)
//                .setAutoCancel(true)
//                .setTicker("有新消息")
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle("一条新通知")
//                .setContentText("您好，你收到了一条新通知")
//                .setDefaults(Notification.DEFAULT_SOUND)
//                .build();


    }
}
