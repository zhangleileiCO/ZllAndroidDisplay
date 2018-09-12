package com.zhangleilei.myviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class Indicator extends View{
    private Paint mForePaint;
    private Paint mBgPaint;
    private float mOffset;
    private int number=3;
    private int radius=10;
    private  int mForeColor= Color.BLUE;
    private int mBgColor=Color.RED;

    /**
     *设置偏移
     * @param position 位置
     * @param positionOffset 位置偏移
     */
    private void setPositionOffset(int position,int positionOffset){
        position %= number;
        mOffset = (position + positionOffset) * number * radius;
        invalidate();
    }
    public Indicator(Context context){
        super(context);
        initPaint();
    }
    public Indicator(Context context, AttributeSet attributeSet){
        super(context,attributeSet);

        TypedArray typedArray=context.obtainStyledAttributes(attributeSet,R.styleable.Indicator);
        number=typedArray.getInteger(R.styleable.Indicator_indicator_number,number);
        radius=typedArray.getInteger(R.styleable.Indicator_indicator_radius,radius);
        mForeColor=typedArray.getColor(R.styleable.Indicator_indicator_selectedColor,mForeColor);
        mBgColor=typedArray.getColor(R.styleable.Indicator_indicator_normalColor,mBgColor);
        initPaint();
    }
    private void initPaint(){
        mForePaint=new Paint();
        mForePaint.setAntiAlias(true);
        mForePaint.setStyle(Paint.Style.FILL);
        mForePaint.setColor(mForeColor);
        mForePaint.setStrokeWidth(2);

        mBgPaint=new Paint();
        mBgPaint.setAntiAlias(true);
        mBgPaint.setStyle(Paint.Style.FILL);
        mBgPaint.setColor(mBgColor);
        mBgPaint.setStrokeWidth(2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width=getWidth();
        int start=width/2-(radius*2*number +radius*(number-1))/2+radius;
        for (int i=0;i<number;i++){
            canvas.drawCircle(start+radius*number*i,radius+1,radius,mBgPaint);
        }
        canvas.drawCircle(start + mOffset,radius+1, radius, mForePaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec),measureHeight(heightMeasureSpec));
    }

    private int measureWidth(int widthMeasureSpec){
        int result=0;
        int specMode=MeasureSpec.getMode(widthMeasureSpec);
        int specSize=MeasureSpec.getSize(widthMeasureSpec);
        if (specMode==MeasureSpec.EXACTLY){
            result = specSize;
        }else if (specMode ==MeasureSpec.AT_MOST){
            result = radius * 2 * number + radius * (number-1) + 2;
        }else {
            result =100;
        }
        return result;
    }
    private int measureHeight(int heightMeasureSpec){
        int result = 0;
        int specMode = MeasureSpec.getMode(heightMeasureSpec);
        int specSize = MeasureSpec.getSize(heightMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {
            //小圆点直径 + 画笔宽度（strokeWidth/2 * 2）
            result = radius * 2 + 2;
        } else {
            result = 100;
        }
        return result;
    }
}
