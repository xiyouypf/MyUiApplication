package com.ypf.myuiapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.ypf.myuiapplication.R;
import com.ypf.myuiapplication.drawable.MyFishDrawable;

public class MyFishDrawableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fish_drawable);
//        ImageView ivFish = findViewById(R.id.iv_fish);
//        ivFish.setImageDrawable(new MyFishDrawable());
    }
}