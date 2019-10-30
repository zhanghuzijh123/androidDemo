package com.example.androiddemo.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.androiddemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewAnimActivity extends AppCompatActivity {
    @BindView(R.id.viewAnimBtn)
    Button vAnimBtn;
    @BindView(R.id.viewAnimBtn1)
    Button vAnimBtn1;
    @BindView(R.id.viewAnimBtn2)
    Button vAnimBtn2;
    @BindView(R.id.viewAnimBtn3)
    Button vAnimBtn3;
    @BindView(R.id.viewAnimTx)
    TextView vAnimTx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_anim);
        ButterKnife.bind(this);

        initBtn();
    }

    private void initBtn() {
        vAnimBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation= AnimationUtils.loadAnimation(ViewAnimActivity.this,R.anim.move_anim);
                vAnimTx.setVisibility(View.VISIBLE);
                vAnimTx.startAnimation(animation);
            }
        });

        vAnimBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation= AnimationUtils.loadAnimation(ViewAnimActivity.this,R.anim.scale_anim);
                vAnimTx.setVisibility(View.VISIBLE);
                vAnimTx.startAnimation(animation);
            }
        });

        vAnimBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation= AnimationUtils.loadAnimation(ViewAnimActivity.this,R.anim.rotate_anim);
                vAnimTx.setVisibility(View.VISIBLE);
                vAnimTx.startAnimation(animation);
            }
        });

        vAnimBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation= AnimationUtils.loadAnimation(ViewAnimActivity.this,R.anim.alpha_anim);
                vAnimTx.setVisibility(View.VISIBLE);
                vAnimTx.startAnimation(animation);
            }
        });
    }
}
