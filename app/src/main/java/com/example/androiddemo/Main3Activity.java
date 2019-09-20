package com.example.androiddemo;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main3Activity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.back_data)
    Button back_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);
        back_data.setOnClickListener(this);
        saveData();
    }

    private void saveData() {
        Intent intent=new Intent();
        intent.putExtra("name","zjh");
        setResult(RESULT_OK,intent);
    }

    //    重写back键，使点击back键也能返回数据
    @Override
    public void onBackPressed() {
        saveData();
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_data:
                saveData();
                finish();
        }
    }
}
