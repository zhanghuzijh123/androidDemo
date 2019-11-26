package com.example.androiddemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoreTypeActivity extends AppCompatActivity {
    @BindView(R.id.recyclerView_more_type)
    RecyclerView recyclerView_more_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_type);

        ButterKnife.bind(this);
    }
}
