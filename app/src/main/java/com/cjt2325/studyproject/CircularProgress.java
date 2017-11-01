package com.cjt2325.studyproject;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;


public class CircularProgress extends View {

    private int center_viewX;
    private int center_viewY;

    private int strokeWidth;

    private int[] strokes = new int[3];

    private int spaceWidth;
    private int radius;

    private Path path;
    private Paint mPaint;

    float[] circular_progress = {0, 0, 0};

    public CircularProgress(Context context) {
        this(context, null);
    }

    public CircularProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircularProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        path = new Path();
        strokeWidth = 90;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
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
        radius = getWidth() < getHeight() ? getWidth() / 2 : getHeight() / 2;
        strokeWidth = (int) (radius / 4.5);
        spaceWidth = strokeWidth / 9;
        mPaint.setStrokeWidth(strokeWidth);

        rectF_1 = new RectF(
                center_viewX - (radius - strokeWidth / 2),
                center_viewY - (radius - strokeWidth / 2),
                center_viewX + (radius - strokeWidth / 2),
                center_viewY + (radius - strokeWidth / 2));
        rectF_2 = new RectF(
                center_viewX - ((float) (radius - strokeWidth * 1.5) - spaceWidth),
                center_viewY - ((float) (radius - strokeWidth * 1.5) - spaceWidth),
                center_viewX + ((float) (radius - strokeWidth * 1.5) - spaceWidth),
                center_viewY + ((float) (radius - strokeWidth * 1.5) - spaceWidth));
        rectF_3 = new RectF(
                center_viewX - ((float) (radius - strokeWidth * 2.5) - 2 * spaceWidth),
                center_viewY - ((float) (radius - strokeWidth * 2.5) - 2 * spaceWidth),
                center_viewX + ((float) (radius - strokeWidth * 2.5) - 2 * spaceWidth),
                center_viewY + ((float) (radius - strokeWidth * 2.5) - 2 * spaceWidth));
        strokes[0] = strokeWidth;
        strokes[1] = strokeWidth;
        strokes[2] = strokeWidth;
    }

    private RectF rectF_1;
    private RectF rectF_2;
    private RectF rectF_3;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(0xff660000);
        canvas.drawCircle(center_viewX, center_viewY, radius - strokeWidth / 2, mPaint);
        mPaint.setColor(0xff666600);
        canvas.drawCircle(center_viewX, center_viewY, (float) (radius - strokeWidth * 1.5) - spaceWidth, mPaint);
        mPaint.setColor(0xff000066);
        canvas.drawCircle(center_viewX, center_viewY, (float) (radius - strokeWidth * 2.5) - 2 * spaceWidth, mPaint);

        if (circular_progress[0] > 0) {
            mPaint.setColor(0xffff0000);
            mPaint.setStrokeWidth(strokes[0]);
            path.reset();
            path.arcTo(rectF_1, -90, circular_progress[0], true);
            canvas.drawPath(path, mPaint);
            calculateItemPositions(canvas, path, strokes[0]);
        }
        if (circular_progress[0] > 0) {
            mPaint.setColor(0xffffff00);
            mPaint.setStrokeWidth(strokes[1]);
            path.reset();
            path.arcTo(rectF_2, -90, circular_progress[1], true);
            canvas.drawPath(path, mPaint);
            calculateItemPositions(canvas, path, strokes[1]);
        }
        if (circular_progress[0] > 0) {
            mPaint.setColor(0xff0000ff);
            mPaint.setStrokeWidth(strokes[2]);
            path.reset();
            path.arcTo(rectF_3, -90, circular_progress[2], true);
            canvas.drawPath(path, mPaint);
            calculateItemPositions(canvas, path, strokes[2]);
        }
    }

    public void updateProgress(final float[] progress) {
        for (float pro : progress) {
            if (pro < 0 || pro > 100) {
                throw new IllegalArgumentException("progress must between 0 and 100");
            }
        }
        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                for (int i = 0; i < circular_progress.length; i++) {
                    circular_progress[i] = 360 * progress[i] / 100f * value;
                }
                invalidate();
            }
        });
        animator.setDuration(1000);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();
    }

    private void calculateItemPositions(Canvas canvas, Path path, int radius) {
        PathMeasure measure = new PathMeasure(path, false);
        float[] startPoint = new float[]{0f, 0f};
        float[] endPoint = new float[]{0f, 0f};
        measure.getPosTan(0, startPoint, null);
        measure.getPosTan(measure.getLength(), endPoint, null);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(startPoint[0], startPoint[1], radius / 2, mPaint);
        canvas.drawCircle(endPoint[0], endPoint[1], radius / 2, mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
    }
}
