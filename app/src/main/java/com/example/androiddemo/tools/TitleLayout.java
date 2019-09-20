package com.example.androiddemo.tools;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.androiddemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

//自定义标题栏
public class TitleLayout extends LinearLayout {
    @BindView(R.id.back)
    Button back;
    @BindView(R.id.more)
    Button more;
    @BindView(R.id.title)
    TextView title;

    public TitleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.titlebar,this);
        ButterKnife.bind(this);

        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)getContext()).finish();
            }
        });

        more.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"more",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
