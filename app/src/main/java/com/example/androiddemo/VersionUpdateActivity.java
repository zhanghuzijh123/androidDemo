package com.example.androiddemo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.androiddemo.tools.CommonDialog;
import com.example.androiddemo.tools.DownloadApk;
import com.example.androiddemo.tools.OnMyNegativeListener;
import com.example.androiddemo.tools.OnMyPositiveListener;
import com.example.androiddemo.tools.net.NetData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VersionUpdateActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.versionBtn)
    Button versionBtn;
    private String newVersion;
    private String nowVersion;
    private static final String TAG = "VersionUpdateActivity";
    private static final int REQUEST_CODE_APP_INSTALL = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version_update);
        ButterKnife.bind(this);

        versionBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.versionBtn:
                getNewVersion();
                break;
        }
    }

    private void getNewVersion() {
        Thread t= new Thread(new Runnable(){
            @Override
            public void run() {
                //通知主线程数据发送成功
                Message message=handler.obtainMessage();
                try{
                    message.what=1;
                    StringBuilder text = new StringBuilder();
                        try {
                            String str = "";
                            URL url = new URL("http://115.220.1.144:8802/up/version.txt");
                            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                            while ((str = in.readLine()) != null) {
                                text.append(str);
                            }
                            in.close();
                        message.obj=text;
                    } catch (MalformedURLException e1) {
                    } catch (IOException e) {
                    }
                    Log.d(TAG, "线程数据发送");
//                    message.obj="2.0";
                    handler.sendMessage(message);
                }catch(Exception e){
                    message.what=20;
                    message.obj=e.toString();
                    e.printStackTrace();
                    handler.sendMessage(message);
                }
            }

        });
        t.start();
    }

    private void updateVersion() {
        getNowVersion(this);
        Log.d(TAG, "updateVersion-new: "+newVersion);
        Log.d(TAG, "updateVersion-now: "+nowVersion);
        if(nowVersion.equals(newVersion)){

        }else{
            Log.d(TAG, "更新版本");
            showUpdaloadDialog(NetData.downloadurl);
        }
    }

    private void getNowVersion(Context context) {
        PackageManager manager = context.getPackageManager();

        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            nowVersion = info.versionName;
            Log.d(TAG, "getNowVersion: "+nowVersion);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                newVersion=msg.obj.toString();
                updateVersion();
                Log.d(TAG, "handleMessage: "+newVersion);
            }
            else if (msg.what == 0) {
                Log.d(TAG, "handleMessage: "+"版本更新失败");
            }
        }
    };

    public void showUpdaloadDialog(final String downloadUrl) {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("已发现最新版本号，是否更新");
        builder.setPositiveButton("更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d(TAG, "更新newVersion： "+newVersion);
                Log.d(TAG, "更新nowVersion: "+nowVersion);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    boolean hasInstallPermission = isHasInstallPermissionWithO(VersionUpdateActivity.this);
                    if (!hasInstallPermission) {
                        showRequestPermissionDialog();
                    } else {
                        //下载apk
                        DownloadApk.getInstance().downLoadApk(VersionUpdateActivity.this, downloadUrl);
                    }
                } else {
                    //Android8.0以下
                    //下载apk
                    DownloadApk.getInstance().downLoadApk(VersionUpdateActivity.this, downloadUrl);
                }
            }
        });
        builder.setNegativeButton("暂不更新",null);
        builder.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private boolean isHasInstallPermissionWithO(Context context) {
        if (context == null) {
            return false;
        }
        return context.getPackageManager().canRequestPackageInstalls();
    }

    private void showRequestPermissionDialog() {
        CommonDialog requestPermission = new CommonDialog(this, "您还没有允许应用安装未知应用权限", "是否去允许", "是", "否",
                new OnMyPositiveListener() {
                    @Override
                    public void onClick() {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            //跳转页面判断允许安卓位置应用权限
                            startInstallPermissionSettingActivity(VersionUpdateActivity.this);
                        }
                    }
                }, new OnMyNegativeListener() {
            @Override
            public void onClick() {
                super.onClick();
            }
        });
        requestPermission.show();
    }

    /**
     * 开启设置安装未知来源应用权限界面
     *
     * @param context
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startInstallPermissionSettingActivity(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
        startActivityForResult(intent, REQUEST_CODE_APP_INSTALL);
    }
}
