package com.example.androiddemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.ColorUtils;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.androiddemo.adapter.LooperPagerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LooperPageActivity extends AppCompatActivity {
    @BindView(R.id.looper)
    ViewPager looper;
    private LooperPagerAdapter looperPagerAdapter;
    private static List<Integer> colors=new ArrayList<>();
    private static final String TAG="LooperPageActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looper_page);
        ButterKnife.bind(this);

        initView();
        Random random=new Random();
        for (int i=0;i<5;i++){
            colors.add(Color.argb(random.nextInt(255),random.nextInt(255),random.nextInt(255),random.nextInt(255)));
        }
        looperPagerAdapter.setData(colors);
        looperPagerAdapter.notifyDataSetChanged();
    }

    private void initView() {
        looperPagerAdapter=new LooperPagerAdapter();
        looper.setAdapter(looperPagerAdapter);
    }
}
