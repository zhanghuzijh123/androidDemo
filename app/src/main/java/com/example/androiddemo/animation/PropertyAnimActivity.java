package com.example.androiddemo.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.androiddemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PropertyAnimActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.propertyAnimBtn)
    Button pAnimBtn;
    @BindView(R.id.propertyAnimText)
    TextView pAnimText;
    @BindView(R.id.propertyAnimBtn1)
    Button pAnimBtn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_anim);
        ButterKnife.bind(this);

        pAnimBtn.setOnClickListener(this);
        pAnimBtn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.propertyAnimBtn:
//                步骤1：设置属性数值的初始值 & 结束值
//                创建动画实例，将ofInt()中传入的int值进行平滑过渡
                ValueAnimator valueAnimator=ValueAnimator.ofInt(pAnimText.getLayoutParams().width,500);
//                步骤2：设置动画的播放各种属性
//                设置动画运行时间
                valueAnimator.setDuration(3000);

//                valueAnimator.setStartDelay(500);
                // 设置动画延迟播放时间

//                valueAnimator.setRepeatCount(0);
                // 设置动画重复播放次数 = 重放次数+1
                // 动画播放次数 = infinite时,动画无限重复

//                valueAnimator.setRepeatMode(ValueAnimator.RESTART);
                // 设置重复播放动画模式
                // ValueAnimator.RESTART(默认):正序重放
                // ValueAnimator.REVERSE:倒序回放

//                步骤3：将改变的值手动赋值给对象的属性值：通过动画的更新监听器
                // 设置 值的更新监听器
                // 即：值每次改变、变化一次,该方法就会被调用一次
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        // 获得改变后的值
                        int currentValue= (int) valueAnimator.getAnimatedValue();
                        // 输出改变后的值
                        System.out.println(currentValue);
                        // 步骤4：将改变后的值赋给对象的属性值
                        pAnimText.getLayoutParams().width=currentValue;
                        // 步骤5：刷新视图，即重新绘制，从而实现动画效果
                        pAnimText.requestLayout();
                    }
                });
//              启动动画
                valueAnimator.start();
                break;

            case R.id.propertyAnimBtn1:

                break;
        }
    }
}
