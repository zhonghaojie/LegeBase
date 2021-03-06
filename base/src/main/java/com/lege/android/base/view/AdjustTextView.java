package com.lege.android.base.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lege.android.base.R;


/**
 * 适用于去除Textview的内边距
 */
@SuppressLint("AppCompatCustomView")
public class AdjustTextView extends TextView {

    private Paint mPaint = getPaint();
    private Rect mBounds = new Rect();
    private boolean isWidthAdjust = false;
    private boolean isHeightAdjust = true;
    public AdjustTextView(Context context) {
        super(context);
    }

    public AdjustTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initParams(context,attrs);
    }

    public AdjustTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initParams(context,attrs);
    }

    private void initParams(Context context, AttributeSet attrs){
        TypedArray tp = context.obtainStyledAttributes(attrs, R.styleable.AdjustTextView);
        isWidthAdjust = tp.getBoolean(R.styleable.AdjustTextView_is_width_adjust,false);
        isHeightAdjust = tp.getBoolean(R.styleable.AdjustTextView_is_height_adjust,true);
        tp.recycle();
    }
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        calculateTextParams();
        int measureWidth = getMeasuredWidth();
        if(isWidthAdjust){
            measureWidth = -mBounds.left + mBounds.right;
        }
        int measureHeight = getMeasuredHeight();
        if(isHeightAdjust){
            measureHeight = -mBounds.top + mBounds.bottom;
        }
        setMeasuredDimension(measureWidth, measureHeight);
    }


    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    protected void onDraw(Canvas canvas) {
        drawText(canvas);
    }


    /**
     * 计算文本参数
     */
    private String calculateTextParams() {
        String text = getText().toString();
        int textLength = text.length();
        mPaint.getTextBounds(text, 0, textLength, mBounds);

        if (textLength == 0) {
            mBounds.right = mBounds.left;
        }
        return text;
    }

    /**
     * 绘制文本
     */
    private void drawText(Canvas canvas) {
        String text = calculateTextParams();
        int left = mBounds.left;
        int bottom = mBounds.bottom;
        mBounds.offset(-mBounds.left, -mBounds.top);
        mPaint.setAntiAlias(true);
        mPaint.setColor(getCurrentTextColor());
        canvas.drawText(text, (float) (-left), (float) (mBounds.bottom - bottom), mPaint);
    }
}
