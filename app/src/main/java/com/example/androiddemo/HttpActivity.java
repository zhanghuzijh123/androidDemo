package com.example.androiddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HttpActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.httpSend)
    Button httpSend;
    @BindView(R.id.httpText)
    TextView httpText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        ButterKnife.bind(this);

        httpSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.httpSend:
                sendRequestWithHttpURLConnection();
                break;
        }
    }

    private void sendRequestWithHttpURLConnection() {
//        开启线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection httpURLConnection=null;
                BufferedReader bufferedReader=null;
                try {
//                    以下两行代码为获取HttpURLConnection实例
                    URL url=new URL("https://www.baidu.com");
                    httpURLConnection= (HttpURLConnection) url.openConnection();
//                    设置HTTP请求方法
                    httpURLConnection.setRequestMethod("GET");
//                    设置连接超时的毫秒数
                    httpURLConnection.setConnectTimeout(8000);
//                    设置读取超时的毫秒数
                    httpURLConnection.setReadTimeout(8000);
//                    获取到服务器返回的数据流
                    InputStream inputStream=httpURLConnection.getInputStream();
                    bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder stringBuilder=new StringBuilder();
                    String line=bufferedReader.readLine();
                    while (line!=null){
                        stringBuilder.append(line);
                    }
                    showResponse(stringBuilder.toString());
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if (bufferedReader!=null){
                        try {
                            bufferedReader.close();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    if (httpURLConnection!=null){
                        httpURLConnection.disconnect();
                    }
                }
            }
        }).start();
    }

    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                在这里进行UI操作，将结果显示到界面上
                httpText.setText(response);
            }
        });
    }
}
