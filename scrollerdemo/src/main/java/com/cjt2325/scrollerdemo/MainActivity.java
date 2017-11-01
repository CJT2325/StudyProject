package com.cjt2325.scrollerdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    RelativeLayout activity_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity_main= (RelativeLayout) findViewById(R.id.activity_main);

    }
}
