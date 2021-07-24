package com.ypf.mymaterialdesign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ypf.mymaterialdesign.viewpager2.ViewPager2Activity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button myViewPager2 = findViewById(R.id.myViewPager2);
        myViewPager2.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.myViewPager2) {
            Intent intent = new Intent(MainActivity.this, ViewPager2Activity.class);
            startActivity(intent);
        }
    }
}