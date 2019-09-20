package com.example.androiddemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.androiddemo.tools.ActivityController;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NormalActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.over)
    Button over;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        ButterKnife.bind(this);

        over.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.over:
                ActivityController.finishAll();
                break;
        }
    }
}
