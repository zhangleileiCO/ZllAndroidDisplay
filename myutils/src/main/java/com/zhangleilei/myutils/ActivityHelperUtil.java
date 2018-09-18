package com.zhangleilei.myutils;

import android.app.Activity;
import android.content.Intent;

public class ActivityHelperUtil {
    private ActivityHelperUtil(){}

    public static void startActivity(Intent intent){
        Activity currentActivity= ActivityManager.getInstance().getTopActivity();
        currentActivity.startActivity(intent);
        currentActivity.overridePendingTransition(R.anim.activity_open_enter, R.anim.activity_open_exit);
    }
    public static void startActivityForResult(Intent intent, int requestCode) {
        Activity currentActivity= ActivityManager.getInstance().getTopActivity();
        currentActivity.startActivityForResult(intent, requestCode);
        currentActivity.overridePendingTransition(R.anim.activity_open_enter, R.anim.activity_open_exit);
    }

    public static void finishActivity(Activity activity) {
        activity.finish();
        activity.overridePendingTransition(R.anim.activity_close_enter, R.anim.activity_close_exit);
    }
    public static void startAndFinishActivity(Intent intent) {
        Activity currentActivity= ActivityManager.getInstance().getTopActivity();
        currentActivity.startActivity(intent);
        currentActivity.finish();
        currentActivity.overridePendingTransition(R.anim.activity_open_enter, R.anim.activity_open_exit);
    }
}
