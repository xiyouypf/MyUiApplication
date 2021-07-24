package com.ypf.mymaterialdesign.md2.tradition;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class NestedTraditionLayout extends LinearLayout {

    private int mTouchSlop;
    private float mLastY;
    private int mTopViewHeight = 0;

    public NestedTraditionLayout(Context context) {
        super(context);
    }

    public NestedTraditionLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledPagingTouchSlop();
    }

    public NestedTraditionLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public NestedTraditionLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //外部拦截法
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        float y = ev.getY();
        if (action == MotionEvent.ACTION_DOWN) {
            mLastY = y;
        } else if (action == MotionEvent.ACTION_MOVE) {
            float dy = mLastY - y;
            if (Math.abs(dy) > mTouchSlop) {
                if (isHiddenTop(dy) || isShowTop(dy)) {
                    return true;//滚动头部
                }
            }
            mLastY = y;
        }
        return super.onInterceptTouchEvent(ev);
    }

    //上滑操作
    private boolean isHiddenTop(float dy) {
        return dy > 0 && getScrollY() < mTopViewHeight;
    }

    //下滑操作
    private boolean isShowTop(float dy) {
        return dy < 0 && getScrollY() > 0 && !canScrollVertically(-1);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float y = event.getY();
        if (action == MotionEvent.ACTION_DOWN) {
            mLastY = y;
        } else if (action == MotionEvent.ACTION_MOVE) {
            float dy = mLastY - y;
            if (Math.abs(dy) > mTouchSlop) {
                scrollBy(0, (int) dy);
            }
            mLastY = y;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void scrollTo(int x, int y) {
        if (y < 0) {
            y = 0;
        }
        if (y > mTopViewHeight) {
            y = mTopViewHeight;
        }
        super.scrollTo(x, y);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTopViewHeight = getChildAt(0).getMeasuredHeight();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        ViewGroup.LayoutParams layoutParams = getChildAt(2).getLayoutParams();
        layoutParams.height = getMeasuredHeight() - getChildAt(1).getMeasuredHeight();
        getChildAt(2).setLayoutParams(layoutParams);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
