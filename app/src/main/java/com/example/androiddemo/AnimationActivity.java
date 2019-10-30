package com.example.androiddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.androiddemo.animation.DrawableAnimActivity;
import com.example.androiddemo.animation.PropertyAnimActivity;
import com.example.androiddemo.animation.ViewAnimActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnimationActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.anim_btn)
    Button anim_btn;
    @BindView(R.id.anim_btn1)
    Button anim_btn1;
    @BindView(R.id.anim_btn2)
    Button anim_btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);

        anim_btn.setOnClickListener(this);
        anim_btn1.setOnClickListener(this);
        anim_btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.anim_btn:
                startActivity(new Intent(this, ViewAnimActivity.class));
                break;

            case R.id.anim_btn1:
                startActivity(new Intent(this, DrawableAnimActivity.class));
                break;

            case R.id.anim_btn2:
                startActivity(new Intent(this, PropertyAnimActivity.class));
                break;
        }
    }
}
