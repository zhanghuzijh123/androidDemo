package com.example.androiddemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.androiddemo.adapter.GaojinAdapter;
import com.example.androiddemo.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewNewDemo extends AppCompatActivity {
    private ArrayList<String> ary=new ArrayList<>();
    private GaojinAdapter gaojinAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView gaojin_rv;
    private OnLoadMoreListener mOnLoadMoreListener;
    private Handler handler=new Handler();
    private int updates=0;  //向上刷新次数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_new_demo);

        initView();
        initDatas();
        initSwipeRefreshLayout();
        initRecyclerView();

        mOnLoadMoreListener=new OnLoadMoreListener() {
            @Override
            protected void onLoading(int countItem, int lastItem) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData("loadMore");
                    }
                }, 2000);
            }
        };
        gaojin_rv.addOnScrollListener(mOnLoadMoreListener);

    }

    private void initSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //模拟网络请求需要3000毫秒，请求完成，设置setRefreshing 为false
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData("refresh");
                    }
                }, 3000);
            }
        });
    }

//    private void getData(final String type) {
//        if ("refresh".equals(type)) {
//            ary.clear();
//            updates = 0;
//            initDatas();
//            initRecyclerView();
//        } else{
//            for (int i = 0; i < 3; i++) {
//                updates += 1;
//            }
//            initRecyclerView();
//        }
//
//        gaojinAdapter.notifyDataSetChanged();
//        if (swipeRefreshLayout.isRefreshing()) {
//            swipeRefreshLayout.setRefreshing(false);
//        }
//        if ("refresh".equals(type)) {
//            Toast.makeText(getApplicationContext(), "刷新完毕", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(getApplicationContext(), "加载完毕", Toast.LENGTH_SHORT).show();
//        }
//    }

    private void getData(final String type) {
        if ("refresh".equals(type)) {
            ary.clear();
            updates = 0;
            initDatas();
        } else{
            for (int i=0;i<3;i++){
                updates+=1;
                ary.add(""+updates);
            }
        }

        gaojinAdapter.notifyDataSetChanged();
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        if ("refresh".equals(type)) {
            Toast.makeText(getApplicationContext(), "刷新完毕", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "加载完毕", Toast.LENGTH_SHORT).show();
        }
    }

    private void initView() {
        gaojin_rv=findViewById(R.id.gaojin_rv);
        swipeRefreshLayout=findViewById(R.id.gaojin_srl);
    }

    private void initRecyclerView() {
//        gaojinAdapter=new GaojinAdapter(RecyclerViewNewDemo.this,ary,updates);
        gaojinAdapter=new GaojinAdapter(RecyclerViewNewDemo.this,ary);
        gaojin_rv.setLayoutManager(new LinearLayoutManager(RecyclerViewNewDemo.this,LinearLayoutManager.VERTICAL,false));
        gaojin_rv.addItemDecoration(new DividerItemDecoration(RecyclerViewNewDemo.this,DividerItemDecoration.VERTICAL));
        gaojin_rv.setAdapter(gaojinAdapter);
    }

    private void initDatas() {
        for (int i=0;i<5;i++){
            ary.add("content"+i);
        }
    }
}
