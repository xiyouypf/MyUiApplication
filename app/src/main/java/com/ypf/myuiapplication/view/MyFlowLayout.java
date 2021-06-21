package com.ypf.myuiapplication.view;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

public class MyFlowLayout extends ViewGroup {

    private static final String TAG = "MyFlowLayout";
    private int mHorizontalSpacing = dp2px(16);//每个item横向间距
    private int mVerticalSpacing = dp2px(8);//每个item纵向间距

    private List<List<View>> allLines = new ArrayList<>();//记录所有的行，一行一行的存储，用于layout
    List<Integer> lineHeights = new ArrayList<>();//记录每一行的行高，用于layout

    //构建
    //四个参数 自定义属性
    public MyFlowLayout(Context context) {
        super(context);
    }

    //反射，解析xml文件使用
    public MyFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //主题style
    public MyFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void cleanMeasureParams() {
        allLines.clear();
        lineHeights.clear();
    }
    //度量
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        cleanMeasureParams();//内存 抖动
        //先度量孩子
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        int selfWidth = MeasureSpec.getSize(widthMeasureSpec);//ViewGroup解析的父亲给我的高度
        int selfHeight = MeasureSpec.getSize(heightMeasureSpec);//ViewGroup解析父亲给我的高度

        int parentNeededWidth = 0;//measure过程中，子View要求的父ViewGroup的宽
        int parentNeededHeight = 0;//measure过程中，子View要求的父ViewGroup的高

        List<View> lineViews = new ArrayList<>();//保存一行中所有的View
        int lineWidthUsed = 0;//记录这一行已经使用了多宽的Size
        int lineHeight = 0;//一行的行高

        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            LayoutParams childLayoutParams = childView.getLayoutParams();
            if (childView.getVisibility()!=View.GONE) {
                //将layoutParams转变成为 measureSpec
                int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, paddingLeft + paddingRight, childLayoutParams.width);
                int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, paddingTop + paddingBottom, childLayoutParams.width);
                childView.measure(childWidthMeasureSpec, childHeightMeasureSpec);

                //获取子View的度量宽高
                int childViewMeasuredWidth = childView.getMeasuredWidth();
                int childViewMeasuredHeight = childView.getMeasuredHeight();

                //如果需要换行
                if (childViewMeasuredWidth + lineWidthUsed + mHorizontalSpacing > selfWidth) {
                    //一旦换行，我们就可以判断当前行苏姚的宽和高了，所以此时要记录下来
                    allLines.add(lineViews);
                    lineHeights.add(lineHeight);
                    //一旦换行，我们就可以判断当前行需要的宽和高了，所以此时要记录下来
                    parentNeededHeight = parentNeededHeight + lineHeight + mVerticalSpacing;
                    parentNeededWidth = Math.max(parentNeededWidth, lineWidthUsed + mHorizontalSpacing);

                    lineViews = new ArrayList<>();
                    lineWidthUsed = 0;
                    lineHeight = 0;
                }

                //View是分行layout的，所以要记录每一行有哪些View，这样可以方便layout布局
                lineViews.add(childView);
                //每一行都有自己的宽和高
                lineWidthUsed = lineWidthUsed + childViewMeasuredWidth + mHorizontalSpacing;
                lineHeight = Math.max(lineHeight, childViewMeasuredHeight);

                //处理最后一行数据
                if (i == childCount - 1) {
                    //一旦换行，我们就可以判断当前行所需的宽和高了，所以此时要记录下来
                    allLines.add(lineViews);
                    lineHeights.add(lineHeight);
                    //一旦换行，我们就可以判断当前行需要的宽和高了，所以此时要记录下来
                    parentNeededHeight = parentNeededHeight + lineHeight + mVerticalSpacing;
                    parentNeededWidth = Math.max(parentNeededWidth, lineWidthUsed + mHorizontalSpacing);
                }
            }
        }

        //再度量自己,保存
        //根据子View的度量结果，来重新度量自己ViewGroup
        //作为一个ViewGroup，它自己也是一个View，他的大小也需要根据他的父亲给他提供的宽高来度量
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int realWidth = (widthMode == MeasureSpec.EXACTLY ? selfWidth : parentNeededWidth);
        int realHeight = (heightMode == MeasureSpec.EXACTLY ? selfHeight : parentNeededHeight);
        setMeasuredDimension(realWidth, realHeight);//传入的是确切值，保存具体的size
    }

    //布局
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int curL = getPaddingLeft();
        int curT = getPaddingTop();
        for (int i = 0; i < allLines.size(); i++) {
            int lineHeight = lineHeights.get(i);
            for (int j = 0; j < allLines.get(i).size(); j++) {
                View view = allLines.get(i).get(j);
                int left = curL;
                int top = curT;
                int right = left + view.getMeasuredWidth();//度量之后的
                int bottom = top + view.getMeasuredHeight();//度量之后的
                view.layout(left, top, right, bottom);
                curL = right + mHorizontalSpacing;
            }
            curT = curT + lineHeight + mVerticalSpacing;
            curL = getPaddingLeft();
        }
    }

    public static int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }
}
