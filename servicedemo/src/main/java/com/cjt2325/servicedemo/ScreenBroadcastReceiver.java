package com.cjt2325.servicedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * =====================================
 * 作    者: 陈嘉桐
 * 版    本：1.1.4
 * 创建日期：2017/10/30
 * 描    述：
 * =====================================
 */
public class ScreenBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            if (Intent.ACTION_SCREEN_ON.equals(action)) {
                Log.i("CJT", "ACTION_SCREEN_ON");
            } else if (Intent.ACTION_SCREEN_OFF.equals(action)) {
                Log.i("CJT", "ACTION_SCREEN_OFF");
            } else if (Intent.ACTION_USER_PRESENT.equals(action)) {
                Log.i("CJT", "ACTION_USER_PRESENT");

            }
        }
    }
}
