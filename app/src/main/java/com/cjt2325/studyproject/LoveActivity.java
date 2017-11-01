package com.cjt2325.studyproject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.king.view.flutteringlayout.FlutteringLayout;

public class LoveActivity extends AppCompatActivity {
    FlutteringLayout flutteringLayout;
    Thread thread;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            flutteringLayout.addHeart();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_love);
        flutteringLayout = (FlutteringLayout) findViewById(R.id.flutteringLayout);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread.start();
            }
        });
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.sendMessage(Message.obtain());
                }
            }
        });
    }

}
