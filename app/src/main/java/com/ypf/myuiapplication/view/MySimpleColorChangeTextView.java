package com.ypf.myuiapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;

public class MySimpleColorChangeTextView extends AppCompatTextView {
    String mText = "学习安卓www";
    private float mPercent = 0.0f;//百分比

    public float getPercent() {
        return mPercent;
    }

    public void setPercent(float mPercent) {
        this.mPercent = mPercent;
        invalidate();//重绘
    }

    public MySimpleColorChangeTextView(Context context) {
        super(context);
    }

    public MySimpleColorChangeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MySimpleColorChangeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override//这里的单位是像素
    protected void onDraw(Canvas canvas) {
        //Canvas画布，Paint画笔
        super.onDraw(canvas);
        drawCenterLineX(canvas);
        drawCenterLineY(canvas);
        drawCenterText(canvas);//底层
        drawCenterText1(canvas);//上面的一层

        //绘制文字
        Paint paint = new Paint();
        paint.setTextSize(20);
        float baseLine = 100;
        canvas.drawText(mText, 0, baseLine, paint);

        //绘制文字,将文字靠近基准线中心对齐
        float left = getWidth() / 2;
        //设置文字第一种文字居中
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(mText, left, baseLine + paint.getFontSpacing() * 2, paint);
    }

    private void drawCenterText(Canvas canvas) {
        canvas.save();
        //绘制文字
        Paint paint = new Paint();
        paint.setTextSize(20);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(3);
        paint.setAntiAlias(true);

        //把文字绘制到view的中心点
        //文字高度的计算
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        //文字的Y轴居中
        float baseLine = getHeight() / 2 + (fontMetrics.descent - fontMetrics.ascent) / 2 -fontMetrics.descent;
        //设置文字第二种X轴居中方式
        float width = paint.measureText(mText);//计算文字的宽度
        float left = getWidth() / 2 - width / 2;
        paint.setTextAlign(Paint.Align.LEFT);
//        //裁剪画布
//        Rect rect = new Rect((int) left, 0, getWidth() / 2, getHeight());
//        canvas.clipRect(rect);
        canvas.drawText(mText, left, baseLine, paint);
        canvas.restore();
    }
    private void drawCenterText1(Canvas canvas) {
        canvas.save();
        //绘制文字
        Paint paint = new Paint();
        paint.setTextSize(20);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(3);
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);

        //把文字绘制到view的中心点
        //文字高度的计算
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        //文字的Y轴居中
        float baseLine = getHeight() / 2 + (fontMetrics.descent - fontMetrics.ascent) / 2 -fontMetrics.descent;
        //设置文字第二种X轴居中方式
        float width = paint.measureText(mText);//计算文字的宽度
        float left = getWidth() / 2 - width / 2;
        float right = left + width * mPercent;
        paint.setTextAlign(Paint.Align.LEFT);
        //裁剪画布
        Rect rect = new Rect((int) left, 0, (int) right, getHeight());
        canvas.clipRect(rect);
        canvas.drawText(mText, left, baseLine, paint);
        canvas.restore();
    }
    private void drawCenterLineX(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);//设置为实线
        paint.setColor(Color.RED);
        paint.setStrokeWidth(3);//设置宽度
        canvas.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight(), paint);
    }

    private void drawCenterLineY(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(3);
        canvas.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2, paint);

    }
}
