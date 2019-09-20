package com.example.androiddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListViewActivity extends AppCompatActivity {
    @BindView(R.id.listView)
    ListView listView;
    private String[] data={"aaa","bbb","ccc","ddd","eee","fff","ggg","hhh","iii"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        ButterKnife.bind(this);

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(ListViewActivity.this,android.R.layout.simple_list_item_1,data);
        listView.setAdapter(arrayAdapter);
    }
}
