package com.example.androiddemo.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.androiddemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrawableAnimActivity extends AppCompatActivity {
    @BindView(R.id.drawableAnimBtn)
    Button dAnimBtn;
    @BindView(R.id.drawableAnimBtn1)
    Button dAnimBtn1;
    @BindView(R.id.drawableAnimView)
    ImageView dAnimView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_anim);
        ButterKnife.bind(this);

        dAnimView.setImageResource(R.drawable.drawable_anim);
        AnimationDrawable animationDrawable= (AnimationDrawable) dAnimView.getDrawable();
        dAnimBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                启动帧动画
                animationDrawable.start();
            }
        });
        dAnimBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                停止帧动画
                animationDrawable.stop();
            }
        });
    }
}
