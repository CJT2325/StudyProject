package com.cjt2325.studyproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CircularActivity extends AppCompatActivity {
    CircularProgress pro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular);
        pro = (CircularProgress) findViewById(R.id.pro);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float[] f = {60, 70, 80};
                pro.updateProgress(f);
            }
        });
    }
}
