package com.cjt2325.studyproject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.ref.WeakReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainActivity extends AppCompatActivity {
    MyProgressBar bar;
    Button btn1, btn2, btn3;

    MyHanlder hanlder = new MyHanlder(this);
    Lock lock = new ReentrantLock();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar = (MyProgressBar) findViewById(R.id.bar);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bar.changeColor(0xff00ff00);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bar.changeColor(0xff0000ff);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bar.reset();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            lock.lock();
                            int i = 0;
                            while (i <= 100) {
                                try {
                                    Thread.sleep(70);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                Message message = Message.obtain();
                                message.what = i;
                                hanlder.sendMessage(message);
                                i++;
                            }
                        } catch (Exception e) {
                        } finally {
                            lock.unlock();
                        }
                        lock.lock();

                    }
                }).start();
            }
        });

    }

    public static class MyHanlder extends Handler {
        WeakReference<Activity> mActivity;

        MyHanlder(MainActivity activity) {
            mActivity = new WeakReference<Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.i("CJT", "handlerMessage");
            MainActivity activity = (MainActivity) mActivity.get();
            activity.bar.updateProgress(msg.what);
        }
    }
}
