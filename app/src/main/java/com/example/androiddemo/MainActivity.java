package com.example.androiddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.btn1)
    Button button1;
    @BindView(R.id.btn2)
    Button button2;
    @BindView(R.id.btn3)
    Button button3;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn1:
                ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("nikoo!");
                progressDialog.setMessage("lulu");
                progressDialog.show();
                break;

            case R.id.btn2:
                AlertDialog.Builder a=new AlertDialog.Builder(MainActivity.this);
                a.setTitle("hello android");
                a.setMessage("12312312");
                a.show();
                startActivity(new Intent(this,ListViewActivity.class));
                break;

            case R.id.btn3:
                startActivity(new Intent(this,ScrollViewActivity.class));
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                        builder.setTitle("nikoo");
                        builder.setMessage("123");
                        builder.show();

                        popupWindow.dismiss();
                        Button button=new Button(MainActivity.this);
        }
    }
}
