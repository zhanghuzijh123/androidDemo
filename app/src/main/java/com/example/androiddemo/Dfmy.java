package com.example.androiddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Dfmy extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.myguanyu)
    TextView myguanyu;
    @BindView(R.id.myshezhi)
    TextView myshezhi;
    @BindView(R.id.myversion)
    TextView myversion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dfmy);

        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.myshezhi:
                Toast.makeText(this,"设置",Toast.LENGTH_SHORT).show();
                break;

            case R.id.myguanyu:
                Toast.makeText(this,"关于",Toast.LENGTH_SHORT).show();
                break;

            case R.id.myversion:
                Toast.makeText(this,"版本更新",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
