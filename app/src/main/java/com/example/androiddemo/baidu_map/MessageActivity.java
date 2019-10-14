package com.example.androiddemo.baidu_map;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.androiddemo.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageActivity extends AppCompatActivity {
    @BindView(R.id.messageTx1)
    TextView tx1;
    @BindView(R.id.messageTx2)
    TextView tx2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message2);
        ButterKnife.bind(this);

        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            tx1.setText(bundle.getString("location"));
            tx2.setText(""+bundle.getInt("id"));
        }
    }
}
