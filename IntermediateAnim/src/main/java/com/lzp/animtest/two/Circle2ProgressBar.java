package com.lzp.animtest.two;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.lzp.animtest.R;

/**
 * @author chen wenwei
 * @Date 2018/11/30
 */
public class Circle2ProgressBar extends View {
    private Paint mPaint;
    private Paint mPaintInner;
    private RectF mRectF = new RectF();
    private int mStrokeWidth = 10;//线条宽度
    private int mNormalColor = Color.parseColor("#FFFFFF");//普通的颜色
    private int mProgressColor = Color.parseColor("#FF4F4F");//已经走了的进度条颜色
    private int mInnerColor = Color.parseColor("#FFF6F6");//填充颜色
    private int mProgress = 0;//进度条
    private Paint.Style mProgressStyle = Paint.Style.STROKE;//填充式还是环形式

    public Circle2ProgressBar(Context context) {
        this(context, null);
    }

    public Circle2ProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Circle2ProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.Circle2ProgressBar);
        mInnerColor = array.getColor(R.styleable.Circle2ProgressBar_inner_color, mInnerColor);
        mStrokeWidth = array.getInteger(R.styleable.Circle2ProgressBar_stroke_width, mStrokeWidth);
        mNormalColor = array.getColor(R.styleable.Circle2ProgressBar_normal_color, mNormalColor);
        mProgressColor = array.getColor(R.styleable.Circle2ProgressBar_progress_color, mProgressColor);
        mProgress = array.getInt(R.styleable.Circle2ProgressBar_progress, mProgress);
        mProgressStyle = array.getInt(R.styleable.Circle2ProgressBar_progress_style, 0) == 0 ? Paint.Style.STROKE : Paint.Style.FILL;
        array.recycle();
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(mNormalColor);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mStrokeWidth);

        mPaintInner = new Paint();
        mPaintInner.setColor(mInnerColor);
        mPaintInner.setAntiAlias(true);
        mPaintInner.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mRectF.set(mStrokeWidth/2, mStrokeWidth/2, MeasureSpec.getSize(widthMeasureSpec) - mStrokeWidth/2, MeasureSpec.getSize(heightMeasureSpec) - mStrokeWidth/2);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(mNormalColor);
        if (mProgress < 360) {
            canvas.drawArc(mRectF, mProgress, 360, mProgressStyle == Paint.Style.FILL, mPaint);
        }
        mPaint.setColor(mProgressColor);
        canvas.drawArc(mRectF, 270, mProgress, mProgressStyle == Paint.Style.FILL, mPaint);
        canvas.drawArc(mRectF, 0, 360, mProgressStyle == Paint.Style.FILL, mPaintInner);
    }

    /**
     * 更新界面
     *
     * @param progress 0-360
     */
    public void update(int progress) {
        this.mProgress = progress;
        postInvalidate();
    }

}