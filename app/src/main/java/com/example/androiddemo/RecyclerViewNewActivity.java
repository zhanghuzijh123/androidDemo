package com.example.androiddemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.androiddemo.adapter.RecyclerViewNewAdapter;
import com.example.androiddemo.model.RecyclerNewView_ex;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewNewActivity extends AppCompatActivity {
    public static final String TAG="RecyclerViewNewActivity";
    private List<RecyclerNewView_ex> datas;
    @BindView(R.id.recyclerNewView)
    RecyclerView recyclerNewView;
    @BindView(R.id.recyclerView_refresh)
    SwipeRefreshLayout recyclerView_refresh;
    private RecyclerViewNewAdapter recyclerViewNewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_new);

        ButterKnife.bind(this);
        
        initData();
        initShow(true,false,true);
        handlerDownPullUpdate();
    }

    private void initListener() {
        recyclerViewNewAdapter.setOnItemClickListener(new RecyclerViewNewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(RecyclerViewNewActivity.this,"这是第"+position+"条数据",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handlerDownPullUpdate() {
        recyclerView_refresh.setEnabled(true);
        recyclerView_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//               执行刷新数据的操作
                RecyclerNewView_ex data=new RecyclerNewView_ex();
                data.num="new";
                data.title="这是一条刚添加的数据";
                datas.add(0,data);
//                更新UI
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        停止刷新并且更新列表
                        recyclerViewNewAdapter.notifyDataSetChanged();
                        recyclerView_refresh.setRefreshing(false);
                    }
                },3000);
            }
        });
    }

    private void initData() {
//        创建模拟数据
        datas=new ArrayList();
        for (int i=1;i<=10000;i++){
            RecyclerNewView_ex data=new RecyclerNewView_ex();
            data.title="这是第"+i+"条数据";
            data.num=i+"、";
            datas.add(data);
        }

    }

    private void initShow(boolean isVertical,boolean isReverse,boolean isDivider) {
//        设置recyclerView的样式
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerNewView.setLayoutManager(linearLayoutManager);
//        设置布局管理器为水平呈现还是垂直显示
        linearLayoutManager.setOrientation(isVertical? LinearLayoutManager.VERTICAL:LinearLayoutManager.HORIZONTAL);
//        设置布局管理器为标准还是反向
        linearLayoutManager.setReverseLayout(isReverse);
//        添加分割线
        recyclerNewView.addItemDecoration(new DividerItemDecoration(this,isDivider?DividerItemDecoration.VERTICAL:DividerItemDecoration.HORIZONTAL));
//        创建适配器
        recyclerViewNewAdapter=new RecyclerViewNewAdapter(datas);
//        设置到recyclerview控件中去
        recyclerNewView.setAdapter(recyclerViewNewAdapter);
//        初始化条目监听事件
        initListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recycler_view_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            //listView部分
            case R.id.listView_vertical_stander:
                Log.d(TAG, "点击了listView_vertical_stander ");
                initShow(true,false,true);
                break;

            case R.id.listView_vertical_reverse:
                Log.d(TAG, "点击了listView_vertical_reverse ");
                initShow(true,true,true);
                break;

            case R.id.listView_horizontal_stander:
                Log.d(TAG, "点击了listView_horizontal_stander ");
                initShow(false,false,false);
                break;

            case R.id.listView_horizontal_reverse:
                Log.d(TAG, "点击了listView_horizontal_reverse ");
                initShow(false,true,false);
                break;

            //gridView部分
            case R.id.gridView_vertical_stander:
                break;

            case R.id.gridView_vertical_reverse:
                break;

            case R.id.gridView_horizontal_stander:
                break;

            case R.id.gridView_horizontal_reverse:
                break;

            //瀑布部分
            case R.id.staggerView_vertical_stander:
                break;

            case R.id.staggerView_vertical_reverse:
                break;

            case R.id.staggerView_horizontal_stander:
                break;

            case R.id.staggerView_horizontal_reverse:
                break;

//                多种条目类型
            case R.id.more_type:
                startActivity(new Intent(this,MoreTypeActivity.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
