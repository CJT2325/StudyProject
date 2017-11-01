package com.cjt2325.plugin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static View parentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (parentView == null) {
            setContentView(R.layout.activity_main);
        } else {
            setContentView(parentView);
        }

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(getApplicationContext(), "I came from 插件~~", Toast.LENGTH_LONG).show();
            }
        });

    }

    public static void setLayoutView(View view) {
        parentView = view;
    }
}
