package com.example.androiddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;


//关于隐式Intent
public class Main2Activity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.finish)
    Button finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ButterKnife.bind(this);

        initData();
        finish.setOnClickListener(this);
    }

    private void initData() {
//        获取上一个活动的数据
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        String password=intent.getStringExtra("password");
        Log.i("Main2Activity", name+password);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.finish:
                finish();
                break;
        }
    }
}
