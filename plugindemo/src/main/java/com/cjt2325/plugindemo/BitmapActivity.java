package com.cjt2325.plugindemo;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class BitmapActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);
        findViewById(R.id.btn).setOnClickListener(this);
        image = (ImageView) findViewById(R.id.image);
    }

    @Override
    public void onClick(View v) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
//        int[] pixel = new int[bitmap.getWidth() * bitmap.getHeight()];
//        bitmap.getPixels(pixel, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
//
//        Log.i("CJT", "======================================================");
////        Log.i("CJT", "length = " + pixel.length);
//        Log.i("CJT", "Alpha = " + Color.alpha(pixel[0]));
//        Log.i("CJT", "Red = " + Color.red(pixel[0]));
//        Log.i("CJT", "Green = " + Color.green(pixel[0]));
//        Log.i("CJT", "Blue = " + Color.blue(pixel[0]));
////        Log.i("CJT", "width = " + bitmap.getWidth() + " ,height = " + bitmap.getHeight());
////        Log.i("CJT", "pixel = " + pixel[0]);
//
//        int red = 0;
//        int green = 0;
//        int blue = 0;
//
//        for (int i = 0; i < pixel.length; i++) {
//            red += Color.red(pixel[0]);
//            green += Color.green(pixel[0]);
//            blue += Color.blue(pixel[0]);
//        }
//        red = (int) (red * 1.0 / pixel.length);
//        green = (int) (green * 1.0 / pixel.length);
//        blue = (int) (blue * 1.0 / pixel.length);
        Bitmap bit = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        int index = 10;
        int x = (int) Math.floor(bitmap.getWidth() / 50.0);
        int y = (int) Math.floor(bitmap.getHeight() / 50.0);
//        Log
        for (int i = 0; i < 1; i++) {
            Log.i("CJT","====1====");
            for (int j = 0; i < 1; j++) {
//                Log.i("CJT","====2====");
                int red = 0;
                int green = 0;
                int blue = 0;

                for (int bx = 0; bx < 50 ; bx++) {
                    for (int by = j * 50; by < (j + 1) * 50 && by < bitmap.getHeight(); by++) {
                        red += Color.red(bitmap.getPixel(bx, by));
                        green += Color.green(bitmap.getPixel(bx, by));
                        blue += Color.blue(bitmap.getPixel(bx, by));

                    }
                }
//                Log.i("CJT","====3====");
                red = (int) (red * 1.0 / 2500);
                green = (int) (green * 1.0 / 2500);
                blue = (int) (blue * 1.0 / 2500);

                for (int bx = i * 50; bx < (i + 1) * 50 && bx < bitmap.getWidth(); bx++) {
                    for (int by = j * 50; by < (j + 1) * 50 && by < bitmap.getHeight(); by++) {
                        bit.setPixel(bx, by, Color.argb(255, red, green, blue));
                    }
                }
//                Log.i("CJT","====4====");
            }
        }

//        Log.i("CJT", "======"+Color.argb(255,red, green, blue));
//        for (int i = 0; i < bitmap.getWidth(); i++) {
//            for (int j = 0; j < bitmap.getHeight(); j++) {
//                int pixel = bitmap.getPixel(i, j);
//                int red = Color.red(pixel);
//                int green = Color.green(pixel);
//                int blue = Color.blue(pixel);
//
//                int gray = (int) (red * 0.299 + green * 0.587 + blue * 0.114);
//                gray = gray > 130 ? 240 : 15;
//
//                bit.setPixel(i, j, Color.argb(255, gray, gray, gray));
//            }
//        }
        image.setImageBitmap(bit);
        Log.i("CJT", "======================================================");
    }
}
