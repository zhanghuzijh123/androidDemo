package com.example.androiddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.androiddemo.adapter.ListViewAdapter;
import com.example.androiddemo.entry.ListDatas;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListViewActivity extends AppCompatActivity {
    private List<ListDatas> datas=new ArrayList<>();
    @BindView(R.id.listview)
    ListView listView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        ButterKnife.bind(this);

        initView();
        ListViewAdapter listViewAdapter=new ListViewAdapter(this,R.layout.list_new_style,datas);
        listView1.setAdapter(listViewAdapter);
    }

    private void initView() {
        for (int i=0;i<10;i++){
            ListDatas a=new ListDatas("a",R.drawable.ic_launcher_background);
            datas.add(a);
            ListDatas b=new ListDatas("b",R.drawable.ic_launcher_background);
            datas.add(b);
            ListDatas c=new ListDatas("c",R.drawable.ic_launcher_background);
            datas.add(c);
            ListDatas d=new ListDatas("d",R.drawable.ic_launcher_background);
            datas.add(d);
            ListDatas e=new ListDatas("e",R.drawable.ic_launcher_background);
            datas.add(e);
        }
    }
}
