package com.example.androiddemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.notificationBtn)
    Button notificationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
        notificationBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.notificationBtn:
                Intent intent=new Intent(this,NormalActivity.class);
                PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,0);
                NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification notification=new Notification.Builder(this)
                        .setContentTitle("通知标题")
                        .setContentText("通知内容:123123123123")
//                        用于指定通知被创建的时间
                        .setWhen(System.currentTimeMillis())
//                        用于设置通知的小图标
                        .setSmallIcon(R.mipmap.ic_launcher)
//                        设置通知的大图标
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
//                        传进跳转参数
                        .setContentIntent(pendingIntent)
//                        点击通知消息之后，自动销毁
                        .setAutoCancel(true)
//                        设置通知的重要程度，最高程度内容会直接以横幅的形式弹出来
                        .build();
                notificationManager.notify(1,notification);
                break;
        }
    }
}
