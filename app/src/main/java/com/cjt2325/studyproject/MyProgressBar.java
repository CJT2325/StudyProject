package com.cjt2325.studyproject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * =====================================
 * 作    者: 陈嘉桐
 * 版    本：1.1.4
 * 创建日期：2017/9/5
 * 描    述：
 * =====================================
 */
public class MyProgressBar extends View {

    private List<Point> lists = new ArrayList<>();

    private Paint mPaint;
    private int defaultColor = 0xffff0000;

    private float offset = 0;
    private float index = offset;

    public MyProgressBar(Context context) {
        this(context, null);
    }

    public MyProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setColor(defStyleAttr);
        mPaint.setAntiAlias(true);
//
//        lists.add(new Point(0xffff0000, 30));
//        lists.add(new Point(0xff00ff00, 60));
//        lists.add(new Point(0xffff00ff, 80));

//        index += 90;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //默认进度条
        mPaint.setColor(0xffcfcfcf);
        mPaint.setStrokeWidth(8);
        canvas.drawLine(offset, getHeight() / 2, getWidth() - offset, getHeight() / 2, mPaint);


        //画颜色
        float start = 0;
        for (Point point : lists) {
            mPaint.setColor(point.color);
            canvas.drawLine(start, getHeight() / 2, point.index, getHeight() / 2, mPaint);
            start = point.index;
        }
        mPaint.setColor(defaultColor);
        canvas.drawLine(start, getHeight() / 2, index, getHeight() / 2, mPaint);

        //圆点
        mPaint.setColor(0xffcccccc);
        canvas.drawCircle(index, getHeight() / 2, 20, mPaint);
        mPaint.setColor(0xffffffff);
        canvas.drawCircle(index, getHeight() / 2, 15, mPaint);
    }

    public void changeColor(int color) {
        lists.add(new Point(defaultColor, index));
        defaultColor = color;
    }

    public void updateProgress(int progress) {
        this.index = getWidth() / 100f * progress;
        invalidate();
    }

    public void reset() {
        lists.clear();
    }

    class Point {
        public int color;
        public float index;

        public Point(int color, float index) {
            this.color = color;
            this.index = index;
        }
    }
}
