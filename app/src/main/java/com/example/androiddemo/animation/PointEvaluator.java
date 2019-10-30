package com.example.androiddemo.animation;

import android.animation.TypeEvaluator;

public class PointEvaluator implements TypeEvaluator {

    // 复写evaluate（）
    // 在evaluate（）里写入对象动画过渡的逻辑
    @Override
    public Object evaluate(float v, Object startValue, Object endValue) {

        // 将动画初始值startValue 和 动画结束值endValue 强制类型转换成Point对象
        PropertyPoint startPoint = (PropertyPoint) startValue;
        PropertyPoint endPoint = (PropertyPoint) endValue;

        // 根据v来计算当前动画的x和y的值
        float x = startPoint.getX() + v * (endPoint.getX() - startPoint.getX());
        float y = startPoint.getY() + v * (endPoint.getY() - startPoint.getY());

        // 将计算后的坐标封装到一个新的Point对象中并返回
        PropertyPoint point = new PropertyPoint(x, y);
        return point;
    }
}
