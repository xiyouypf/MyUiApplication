package com.ypf.myuiapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ypf.myuiapplication.activity.MyFishDrawableActivity;
import com.ypf.myuiapplication.activity.MyFlowLayoutActivity;
import com.ypf.myuiapplication.activity.MyRecycleViewActivity;
import com.ypf.myuiapplication.activity.MyRecyclerViewRecycleActivity;
import com.ypf.myuiapplication.activity.MySimpleColorChangeTextViewActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMyFlowLayout = findViewById(R.id.btn_my_flow_layout);
        btnMyFlowLayout.setOnClickListener(this::onClick);
        Button btnMySimpleColorChangeTextView = findViewById(R.id.btn_my_simple_color_change_text_view);
        btnMySimpleColorChangeTextView.setOnClickListener(this::onClick);
        Button btnMyFishDrawable = findViewById(R.id.btn_my_fish_drawable);
        btnMyFishDrawable.setOnClickListener(this::onClick);
        Button myRecyclerView = findViewById(R.id.btn_my_recycler_view);
        myRecyclerView.setOnClickListener(this::onClick);
        Button myRecyclerViewRecycle = findViewById(R.id.btn_my_recycler_view_recycle);
        myRecyclerViewRecycle.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        //自定义流式布局
        if (v.getId() == R.id.btn_my_flow_layout) {
            Intent intent = new Intent(MainActivity.this, MyFlowLayoutActivity.class);
            startActivity(intent);
        //自定义TextView
        } else if (v.getId() == R.id.btn_my_simple_color_change_text_view) {
            Intent intent = new Intent(MainActivity.this, MySimpleColorChangeTextViewActivity.class);
            startActivity(intent);
        //鲤鱼游动
        } else if (v.getId() == R.id.btn_my_fish_drawable) {
            Intent intent = new Intent(MainActivity.this, MyFishDrawableActivity.class);
            startActivity(intent);
        //RecyclerView吸顶效果
        } else if (v.getId() == R.id.btn_my_recycler_view) {
            Intent intent = new Intent(MainActivity.this, MyRecycleViewActivity.class);
            startActivity(intent);
        //RecyclerView回收复用
        } else if (v.getId() == R.id.btn_my_recycler_view_recycle) {
            Intent intent = new Intent(MainActivity.this, MyRecyclerViewRecycleActivity.class);
            startActivity(intent);
        }
    }
}