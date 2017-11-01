package com.cjt.viewpagedemo;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    ViewPager vp;
    VPAdapter vpAdapter;
    List<View> list;
    RelativeLayout tv;
    RelativeLayout head;
    LinearLayout ll;
    TextView text;
    ImageView image;
    ImageView imagev;
    ImageView imageq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vp = (ViewPager) findViewById(R.id.vp);
        list = new ArrayList<>();
        tv = (RelativeLayout) findViewById(R.id.tv);
        head = (RelativeLayout) findViewById(R.id.head);
        ll = (LinearLayout) findViewById(R.id.ll);
        text = (TextView) findViewById(R.id.text);
        image = (ImageView) findViewById(R.id.image);
        imagev = (ImageView) findViewById(R.id.imagev);
        imageq = (ImageView) findViewById(R.id.imageq);
        ImageView btn1 = new ImageView(this);
//        btn1.setBackgroundColor(0xffff0000);
        btn1.setImageDrawable(getResources().getDrawable(R.drawable.shape));
        ImageView btn2 = new ImageView(this);
        btn2.setBackgroundColor(0xffffffff);
//        ImageView btn3 = new ImageView(this);
//        btn3.setBackgroundColor(0xffff00ff);

        list.add(btn1);
        list.add(btn2);
//        list.add(btn3);
        vpAdapter = new VPAdapter(list);
        vp.setAdapter(vpAdapter);
        vp.addOnPageChangeListener(this);
//        vp.setPageTransformer(true,new DepthPageTransformer());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    int temp;
    int temptop;
    int color;
    int textColor;
    float alpha;
    int height = 80;
    int martop;

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        ViewGroup.LayoutParams pa = head.getLayoutParams();
        pa.height = DensityUtil.dip2px(this, height);
        head.setLayoutParams(pa);
//        Log.i("CJT", "onPageScrolled( " + position + " " + positionOffset + " " + positionOffsetPixels + " )");
        if (position == 0) {
            alpha = 1 - positionOffset;
            temp = DensityUtil.dip2px(MainActivity.this, 16 * (1 - positionOffset));
//            temptop = DensityUtil.dip2px(MainActivity.this, 64 * (1 - positionOffset));
            color = (int) (150 * positionOffset);
            textColor = (int) (150 * (1 - positionOffset)) + 105;
            martop = DensityUtil.dip2px(MainActivity.this, height * positionOffset);
        } else {
            temp = DensityUtil.dip2px(MainActivity.this, 0);
        }
        RelativeLayout.LayoutParams pat = (RelativeLayout.LayoutParams) head.getLayoutParams();
        pat.setMargins(0, -martop, 0, 0);
        head.setLayoutParams(pat);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) tv.getLayoutParams();
        params.setMargins(temp, temptop, temp, temp);
        tv.setLayoutParams(params);
        tv.setBackgroundColor(Color.argb(color + 105, 255, 255, 255));
        text.setTextColor(Color.rgb(textColor, textColor, textColor));
        Drawable drawable = getResources().getDrawable(R.drawable.ic_searcher);
        Drawable drawableq = getResources().getDrawable(R.drawable.ic_qr);
        Drawable drawablev = getResources().getDrawable(R.drawable.ic_voide);
//        DrawableCompat.setTint(drawable, Color.rgb(textColor, textColor, textColor));
        image.setImageDrawable(tintDrawable(drawable, ColorStateList.valueOf(Color.rgb(textColor, textColor,
                textColor))));
        imageq.setImageDrawable(tintDrawable(drawableq, ColorStateList.valueOf(Color.rgb(textColor, textColor,
                textColor))));
        imagev.setImageDrawable(tintDrawable(drawablev, ColorStateList.valueOf(Color.rgb(textColor, textColor,
                textColor))));
        Log.i("CJT", "alpha = " + alpha);
        ll.setAlpha(alpha);
//        Log.i("CJT","Top = "+tv.getTop());
    }

    @Override
    public void onPageSelected(int position) {
//        Log.i("CJT", "onPageSelected( " + position + ")");
    }

    @Override
    public void onPageScrollStateChanged(int state) {
//        Log.i("CJT", "onPageScrollStateChanged( " + state + " )");
    }

    public static Drawable tintDrawable(Drawable drawable, ColorStateList colors) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(wrappedDrawable, colors);
        return wrappedDrawable;
    }
}
