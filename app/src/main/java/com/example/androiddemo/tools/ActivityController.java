package com.example.androiddemo.tools;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;
//  对活动activity进行堆栈，并实现一次性全部清除
public class ActivityController {
    public static List<Activity> activities=new ArrayList<>();

    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public static void finishAll(){
        for (Activity activity :activities){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
