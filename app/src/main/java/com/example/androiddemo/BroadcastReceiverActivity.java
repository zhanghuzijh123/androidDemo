package com.example.androiddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BroadcastReceiverActivity extends AppCompatActivity implements View.OnClickListener {
    private IntentFilter intentFilter;
    private NetWorkChangeReceiver netWorkChangeReceiver;
    @BindView(R.id.broadcastBtn)
    Button broadcastBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver);
        ButterKnife.bind(this);

        broadcastBtn.setOnClickListener(this);

        intentFilter=new IntentFilter();
//        绑定广播状态类型
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
//        创建实例
        netWorkChangeReceiver=new NetWorkChangeReceiver();
//        通过注册，将以上两实例进行绑定
        registerReceiver(netWorkChangeReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        退出时接触绑定进行销毁
        unregisterReceiver(netWorkChangeReceiver);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.broadcastBtn:
//                发送标准广播
//                Intent intent=new Intent("");
//                sendBroadcast(intent);
                break;
        }
    }

    class NetWorkChangeReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
//            该类专门用于管理网络连接
            ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
            if (networkInfo!=null&&networkInfo.isConnected()){
                Toast.makeText(context,"网络已连接",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context,"网络已断开",Toast.LENGTH_SHORT).show();
            }

        }
    }
}
