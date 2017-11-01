package com.cjt2325.studyproject;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * =====================================
 * 作    者: 陈嘉桐
 * 版    本：1.1.4
 * 创建日期：2017/8/7
 * 描    述：
 * =====================================
 */
public class StudyView extends View implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensor;

    private Paint paint;

    private Ball ball;

    private float center_viewX;
    private float center_viewY;

    private float offset_X;
    private float offset_Y;

    public StudyView(Context context) {
        this(context, null);
    }

    public StudyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StudyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public StudyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);//获取service
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);//选取传感器
        sensorManager.registerListener(this, sensor, sensorManager.SENSOR_DELAY_NORMAL);//设置监听器

        offset_X = 0;
        offset_Y = 0;
        paint = new Paint();
        paint.setColor(0xFFFF0000);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        center_viewX = getWidth() / 2;
        center_viewY = getHeight() / 2;

        ball = new Ball(center_viewX, center_viewY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(ball.x, ball.y, 100, paint);
    }

    private boolean isRunning = false;

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (!isRunning) {
            Log.i("CJT", "Running ...");
            isRunning = true;
            ValueAnimator anim = ValueAnimator.ofObject(new BallEvaluator(), ball, new Ball( event
                    .values[0] * 10,  - event.values[1] * 10));
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    ball = (Ball) animation.getAnimatedValue();
                    invalidate();
                }
            });
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    isRunning = false;
                    Log.i("CJT", "End ...");
                }
            });
            anim.setDuration(500);
            anim.start();
        }
//        Log.i("CJT", "X = " + event.values[0] + " Y = " + event.values[1] + " Z = " + event.values[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    class Ball {
        public float x;
        public float y;

        public Ball(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    class BallEvaluator implements TypeEvaluator {
        @Override
        public Object evaluate(float fraction, Object startValue, Object endValue) {
            Ball startPoint = (Ball) startValue;
            Ball endPoint = (Ball) endValue;
            float x = startPoint.x + fraction * (endPoint.x - startPoint.x);
            float y = startPoint.y + fraction * (endPoint.y - startPoint.y);
            Ball ball = new Ball(x, y);
            return ball;
        }
    }
}
