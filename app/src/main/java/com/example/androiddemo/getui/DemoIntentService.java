package com.example.androiddemo.getui;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.androiddemo.BaiduMap;
import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTNotificationMessage;
import com.igexin.sdk.message.GTTransmitMessage;

public class DemoIntentService extends GTIntentService {
    private static final String TAG = "GetuiSdkDemo";

    @Override
    public void onReceiveServicePid(Context context, int i) {

    }

    @Override
    public void onReceiveClientId(Context context, String s) {

    }

    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage gtTransmitMessage) {
        byte[] payload = gtTransmitMessage.getPayload();
        String cid=gtTransmitMessage.getClientId();
        String mid=gtTransmitMessage.getMessageId();
        String tid=gtTransmitMessage.getTaskId();
        String aid=gtTransmitMessage.getAppid();
        String pkgName=gtTransmitMessage.getPkgName();

        Log.d(TAG,"cid"+cid+"\nmid"+mid+"\ntid"+tid+"\naid"+aid+"\npkgname"+pkgName);
        if (payload == null) {
            Log.e(TAG, "receiver payload = null");
        } else {
            String data = new String(payload);
            Log.d(TAG, "receiver payload = " + data);

            // 测试消息为了观察数据变化
            if (data!=null) {
                Intent intent=new Intent(this,BaiduMap.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
                getApplication().startActivity(intent);
            }
        }

    }

    @Override
    public void onReceiveOnlineState(Context context, boolean b) {

    }

    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage gtCmdMessage) {

    }

    // 通知到达，只有个推通道下发的通知会回调此方法
    @Override
    public void onNotificationMessageArrived(Context context, GTNotificationMessage gtNotificationMessage) {
        System.out.println("1111111");
    }

    // 通知点击，只有个推通道下发的通知会回调此方法
    @Override
    public void onNotificationMessageClicked(Context context, GTNotificationMessage gtNotificationMessage) {
        System.out.println("2222222");
    }
}
