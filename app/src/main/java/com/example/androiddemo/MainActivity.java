package com.example.androiddemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androiddemo.getui.DemoIntentService;
import com.example.androiddemo.getui.DemoPushService;
import com.igexin.sdk.PushManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.btn1)
    Button button1;
    @BindView(R.id.btn2)
    Button button2;
    @BindView(R.id.btn3)
    Button button3;
    @BindView(R.id.btn4)
    Button button4;
    @BindView(R.id.btn5)
    Button button5;
    @BindView(R.id.btn6)
    Button button6;
    @BindView(R.id.btn7)
    Button button7;
    @BindView(R.id.btn8)
    Button button8;
    @BindView(R.id.btn9)
    Button button9;
    @BindView(R.id.btn10)
    Button button10;
    @BindView(R.id.btn11)
    Button button11;
    @BindView(R.id.btn12)
    Button button12;
    @BindView(R.id.btn13)
    Button button13;
    @BindView(R.id.btn14)
    Button button14;
    @BindView(R.id.btn15)
    Button button15;
    @BindView(R.id.btn16)
    Button button16;
    @BindView(R.id.btn17)
    Button button17;
    @BindView(R.id.btn18)
    Button button18;
    @BindView(R.id.btn19)
    Button button19;
    @BindView(R.id.btn21)
    Button button21;
    @BindView(R.id.btn22)
    Button button22;
    @BindView(R.id.proTx)
    TextView textView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    private int num=30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        个推初始化
        PushManager.getInstance().initialize(getApplicationContext(), DemoPushService.class);
//        在个推SDK初始化后，注册上述 IntentService 类
        PushManager.getInstance().registerPushIntentService(getApplicationContext(), DemoIntentService.class);

        initView();
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button10.setOnClickListener(this);
        button11.setOnClickListener(this);
        button12.setOnClickListener(this);
        button13.setOnClickListener(this);
        button14.setOnClickListener(this);
        button15.setOnClickListener(this);
        button16.setOnClickListener(this);
        button17.setOnClickListener(this);
        button18.setOnClickListener(this);
        button19.setOnClickListener(this);
        button21.setOnClickListener(this);
        button22.setOnClickListener(this);
    }

    private void initView() {
        String n=num+"";
        textView.setText(n);
    }

    //    标题菜单栏
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                Toast.makeText(this,"addMenu",Toast.LENGTH_SHORT).show();
                break;

            case R.id.remove_item:
                Toast.makeText(this,"removeMenu",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

//    得到下一个活动传上来的数据信息
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String name = data.getStringExtra("name");
                    Log.i("MainActivity", "onActivityResult: " + name);
                }
                break;
            default:
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn1:
                num++;
                progressBar.incrementProgressBy(1);
                initView();
//                ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);
//                progressDialog.setMax(100);
//                progressDialog.setProgress(30);
//                progressDialog.incrementProgressBy(1);
//                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//                progressDialog.setTitle("nikoo!");
//                progressDialog.setMessage("lulu");
//                progressDialog.show();
                break;

//                隐式Intent跳转
            case R.id.btn2:
//                startActivity(new Intent("com.example.androiddemo.ACTION_INTENT"));
                Intent intent=new Intent("com.example.androiddemo.ACTION_INTENT");
                intent.putExtra("name","nikoo");
                intent.putExtra("password","123456");
                startActivity(intent);
                break;

            case R.id.btn3:
                startActivityForResult(new Intent(this,Main3Activity.class),1);
                break;

            case R.id.btn4:
                startActivity(new Intent(this,ActivityLife.class));
                break;

//            case R.id.btn5:
//                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
//                builder.setTitle("Hello dialog!");
//                builder.setMessage("nikoo,this is a dialog");
//                builder.setCancelable(false);
//                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                });
//                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                });
//                builder.show();
//                break;
            case R.id.btn5:
                startActivity(new Intent(this,ListViewActivity.class));
                break;

            case R.id.btn6:
                startActivity(new Intent(this,RecyclerViewActivity.class));
                break;

            case R.id.btn7:
                startActivity(new Intent(this,BroadcastReceiverActivity.class));
                break;

            case R.id.btn8:
                startActivity(new Intent(this,NotificationActivity.class));
                break;

            case R.id.btn9:
                startActivity(new Intent(this,WebViewActivity.class));
                break;

            case R.id.btn10:
                startActivity(new Intent(this,HttpActivity.class));
                break;

            case R.id.btn11:
                startActivity(new Intent(this,OKHttpActivity.class));
                break;

            case R.id.btn12:
                startActivity(new Intent(this,MessageActivity.class));
                break;

            case R.id.btn13:
                startActivity(new Intent(this,ServiceActivity.class));
                break;

            case R.id.btn14:
                startActivity(new Intent(this,ToolBarActivity.class));
                break;

            case R.id.btn15:
                startActivity(new Intent(this,BaiduMap.class));
                break;

            case R.id.btn16:
                startActivity(new Intent(this,ConstraintLayoutActivity.class));
                break;

            case R.id.btn17:
                startActivity(new Intent(this,AnimationActivity.class));
                break;

            case R.id.btn18:
                startActivity(new Intent(this,ZxingActivity.class));
                break;

            case R.id.btn19:
                startActivity(new Intent(this,ServiceNewActivity.class));
                break;

            case R.id.btn21:
                startActivity(new Intent(this,RecyclerViewNewActivity.class));
                break;

            case R.id.btn22:
                startActivity(new Intent(this,RecyclerViewNewDemo.class));
                break;
        }
    }
}
