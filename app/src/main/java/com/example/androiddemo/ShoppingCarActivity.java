package com.example.androiddemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.androiddemo.adapter.GaojinAdapter;
import com.example.androiddemo.adapter.ShoppingCarAdapter;
import com.example.androiddemo.tools.TitleLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShoppingCarActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.topBar)
    TitleLayout topbar;
    @BindView(R.id.shopping)
    RecyclerView shopping;
    private ArrayList<String> ary=new ArrayList<>();
    private ShoppingCarAdapter shoppingCarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_car);
        ButterKnife.bind(this);

        initDatas();
        initShoppingCarView();
    }

    private void initDatas() {
        for (int i=0;i<5;i++){
            ary.add("content"+i);
        }
    }

    private void initShoppingCarView() {
        shoppingCarAdapter=new ShoppingCarAdapter(ShoppingCarActivity.this,ary);
        shopping.setLayoutManager(new LinearLayoutManager(ShoppingCarActivity.this,LinearLayoutManager.VERTICAL,false));
        shopping.addItemDecoration(new DividerItemDecoration(ShoppingCarActivity.this,DividerItemDecoration.VERTICAL));
        shopping.setAdapter(shoppingCarAdapter);
    }

    @Override
    public void onClick(View view) {

    }
}
