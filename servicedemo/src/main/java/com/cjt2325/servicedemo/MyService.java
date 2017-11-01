package com.cjt2325.servicedemo;

import android.app.Notification;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * =====================================
 * 作    者: 陈嘉桐
 * 版    本：1.1.4
 * 创建日期：2017/10/19
 * 描    述：
 * =====================================
 */
public class MyService extends Service {
    int foregroundID = 1;

    @Override
    public void onCreate() {
        super.onCreate();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    Log.e("CJT", "MyService 正在运行~");
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(foregroundID, new Notification());
        bindService(new Intent(this, RemoteService.class), mServiceConnection, Context.BIND_IMPORTANT);
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
//        return new ProcessConnection.Stub();
        return new ProcessConnection.Stub() {
        };
    }

    ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("CJT", "远程服务 连接");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("CJT", "远程服务 断开");
            Toast.makeText(MyService.this, "远程服务 断开", Toast.LENGTH_SHORT).show();
            startService(new Intent(MyService.this, RemoteService.class));
            bindService(new Intent(MyService.this, RemoteService.class), mServiceConnection, Context.BIND_IMPORTANT);
        }
    };
}
