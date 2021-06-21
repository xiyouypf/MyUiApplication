package com.ypf.myuiapplication.adapeter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//自定义分割线
public class StarDecoration extends RecyclerView.ItemDecoration {
    private Context context;
    private int groupHeaderHeight;//组头部的高度
    private final Paint headPaint;//头部画笔
    private final Paint textPaint;//文字画笔
    private final Rect textRect;

    public StarDecoration(Context context) {
        this.context = context;
        groupHeaderHeight = dp2px(context, 100);

        headPaint = new Paint();
        headPaint.setColor(Color.RED);

        textPaint = new Paint();
        textPaint.setTextSize(50);
        textPaint.setColor(Color.WHITE);
        textRect = new Rect();
    }

    //在各个item之间的预留空间中画文字或分割线
    @Override//不能覆盖item
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (parent.getAdapter() instanceof StarAdapter) {
            StarAdapter adapter = (StarAdapter) parent.getAdapter();
            int count = parent.getChildCount();//看得见的字VIEW数量，这个不等于list的size
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();
            for (int i = 0; i < count; i++) {
                View view = parent.getChildAt(i);//获取对应i的VIEW
                int position = parent.getChildLayoutPosition(view);//获取VIEW的布局位置
                boolean isGroupHeader = adapter.isGroupHeader(position);//是否是头部
                if (isGroupHeader && view.getTop() - groupHeaderHeight - parent.getPaddingTop() >= 0) {
                    c.drawRect(left, view.getTop() - groupHeaderHeight, right, view.getTop(), headPaint);
                    String groupName = adapter.getGroupName(position);
                    textPaint.getTextBounds(groupName, 0, groupName.length(), textRect);
                    c.drawText(groupName, left + 20, view.getTop() - groupHeaderHeight / 2 + textRect.height() / 2, textPaint);
                } else if (view.getTop() - groupHeaderHeight - parent.getPaddingTop() >= 0){
                    c.drawRect(left, view.getTop() - 4, right, view.getTop(), headPaint);//分割线
                }
            }
        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        if (parent.getAdapter() instanceof StarAdapter) {
            StarAdapter adapter = (StarAdapter) parent.getAdapter();
            //返回可见区域内的第一个item的position
            LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
            int position = layoutManager.findFirstVisibleItemPosition();
            //获取对应position的view
            View itemView = parent.findViewHolderForAdapterPosition(position).itemView;
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();
            int top = parent.getPaddingTop();
            //当第二个是组的头部的时候
            boolean isGroupHeader = adapter.isGroupHeader(position + 1);
            if (isGroupHeader) {
                int bottom = Math.min(groupHeaderHeight, itemView.getBottom() - parent.getPaddingTop());
                c.drawRect(left, top, right, top + bottom, headPaint);
                String groupName = adapter.getGroupName(position);
                textPaint.getTextBounds(groupName, 0, groupName.length(), textRect);
                c.drawText(groupName, left + 20, top + bottom
                        - groupHeaderHeight / 2 + textRect.height() / 2, textPaint);
            } else {
                c.drawRect(left, top, right, top + groupHeaderHeight, headPaint);
                String groupName = adapter.getGroupName(position);
                textPaint.getTextBounds(groupName, 0, groupName.length(), textRect);
                c.drawText(groupName, left + 20, top + groupHeaderHeight / 2 + textRect.height() / 2, textPaint);
            }
        }
    }

    //主要是各个item之间预留空间，预留边距
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getAdapter() instanceof StarAdapter) {
            StarAdapter adapter = (StarAdapter) parent.getAdapter();
            int position = parent.getChildLayoutPosition(view);//获取item在集合中的position
            boolean isGroupHeader = adapter.isGroupHeader(position);
            if (isGroupHeader) {//如果是头部,预留更大的地方
                outRect.set(0, groupHeaderHeight, 0, 0);//view的getTop方法不包括这部分
            } else {//如果不是头部
                outRect.set(0, 4, 0, 0);//预留1像素的空间
            }
        }
    }

    //dp转为px
    private int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale * 0.5f);
    }
}
