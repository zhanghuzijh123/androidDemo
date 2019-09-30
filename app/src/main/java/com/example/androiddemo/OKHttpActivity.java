package com.example.androiddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OKHttpActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.okHttpBtn)
    Button okHttpBtn;
    @BindView(R.id.okHttpText)
    TextView okHttpText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        ButterKnife.bind(this);

        okHttpBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.okHttpBtn:
                sendRequestWithOKHttp();
                break;
        }
    }

    private void sendRequestWithOKHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                    创建一个OKHttpClient实例
                    OkHttpClient client=new OkHttpClient();
//                    创建一个request对象，发起请求
                    Request request=new Request.Builder()
                            .url("http://www.baidu.com")
                            .build();
//                    发送请求并获取服务器返回数据
                    Response response=client.newCall(request).execute();
//                    得到返回的具体内容
                    String responseData=response.body().string();
                    showResponse(responseData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                在这里进行UI操作，将结果显示到界面上
                okHttpText.setText(response);
            }
        });
    }
}
