package com.example.androiddemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

//待定！！
public class VideoActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.mvStart)
    Button mvStart;
    @BindView(R.id.mvPause)
    Button mvPause;
    @BindView(R.id.mvEnd)
    Button mvEnd;
    private MediaPlayer mediaPlayer=new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);

        mvStart.setOnClickListener(this);
        mvPause.setOnClickListener(this);
        mvEnd.setOnClickListener(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            },1);
        }else {
            initMediaPlayer();
        }
    }

    private void initMediaPlayer() {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mvStart:
                break;

            case R.id.mvPause:
                break;

            case R.id.mvEnd:
                break;
        }
    }
}
