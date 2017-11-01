package com.cjt2325.host;

import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(this);
        imageView = (ImageView) findViewById(R.id.image);
    }

    @Override
    public void onClick(View v) {
        try {
            String pluginURL = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "plugin" +
                    ".apk";
            Log.i("CJT", pluginURL);
            AssetManager manager = AssetManager.class.newInstance();
            Method method = AssetManager.class.getDeclaredMethod("addAssetPath", String.class);
            method.invoke(manager, pluginURL);
            Resources resources = new Resources(manager, new DisplayMetrics(), new Configuration());
            int id = getMipmapID(resources, "p2");
            Drawable drawable = resources.getDrawable(id);
            imageView.setImageDrawable(drawable);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getDrawableID(Resources resources, String name) {
        return resources.getIdentifier(name, "drawable", "com.cjt2325.plugin");
    }

    public int getMipmapID(Resources resources, String name) {
        return resources.getIdentifier(name, "mipmap", "com.cjt2325.plugin");
    }

    public AssetManager getAssetManager() {
        try {
            String pluginURL = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "plugin" +
                    ".apk";
            AssetManager manager = AssetManager.class.newInstance();
            Method method = AssetManager.class.getDeclaredMethod("addAssetPath", String.class);
            method.invoke(manager, pluginURL);
            Resources resources = new Resources(manager, new DisplayMetrics(), new Configuration());
            int id = getMipmapID(resources, "p2");
            Drawable drawable = resources.getDrawable(id);
            imageView.setImageDrawable(drawable);
            return manager;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
