package com.cjt2325.plugindemo2;

import android.app.Application;
import android.content.Context;

import com.morgoo.droidplugin.PluginHelper;

/**
 * =====================================
 * 作    者: 陈嘉桐
 * 版    本：1.1.4
 * 创建日期：2017/8/18
 * 描    述：
 * =====================================
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PluginHelper.getInstance().applicationOnCreate(getBaseContext()); //must behind super.onCreate()
    }

    @Override
    protected void attachBaseContext(Context base) {
        PluginHelper.getInstance().applicationAttachBaseContext(base);
        super.attachBaseContext(base);
    }
}
