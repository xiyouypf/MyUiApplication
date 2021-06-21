package com.ypf.myuiapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.ypf.myuiapplication.R;
import com.ypf.myuiapplication.view.MySimpleColorChangeTextView;

public class MySimpleColorChangeTextViewActivity extends AppCompatActivity {
    MySimpleColorChangeTextView mView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_simple_color_change_text_view);
        mView1 = findViewById(R.id.color_change_textview);
        //属性动画
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                onStartLeft(null);
            }
        }, 2000);
    }

    public void onStartLeft(View view) {
        ObjectAnimator.ofFloat(mView1, "percent", 0, 1).setDuration(5000).start();
    } 
}