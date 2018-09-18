package com.zhangleilei.myutils;

import android.app.Activity;

import java.util.Stack;

public class ActivityManager {
    private static ActivityManager instance;
    private static Stack<Activity> activityStack;
    public static ActivityManager getInstance(){
         if (instance ==null){
             synchronized (ActivityManager.class){
                 if (instance==null){
                     instance=new ActivityManager();
                 }
             }
         }
         return instance;
    }

    /**
     * 构造方法
     * 不可实例化的类 提供私有构造方法
     */
    private ActivityManager(){ }


    public void addActivity(Activity activity){
          if (activityStack ==null){
              activityStack=new Stack<>();
          }
          activityStack.push(activity);
    }
    public Activity getTopActivity(){
        return  activityStack.lastElement();
    }
    public void removeActivity(Activity activity){
        if (activity != null) {
            activityStack.remove(activity);
            activity = null;
        }
    }
    public void removeActivity(Class<?> activityClass) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(activityClass)) {
                removeActivity(activity);
            }
        }
    }
    public void removeAllActivity() {
        activityStack.removeAllElements();
    }
}
