package com.ypf.myfragmentandviewpager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ypf.myfragmentandviewpager.fragment.DataCommunicationActivity;
import com.ypf.myfragmentandviewpager.pagerviewandfragment.MyPagerViewFragmentActivity;
import com.ypf.myfragmentandviewpager.viewpager2andfragment.ViewPagerAndFragmentActivity;
import com.ypf.myfragmentandviewpager.wechatpage.WeChatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button dataCommunication = findViewById(R.id.btn_data_communication);
        dataCommunication.setOnClickListener(this::onClick);
        Button viewPagerSimpleUsed = findViewById(R.id.view_pager_simple_used);
        viewPagerSimpleUsed.setOnClickListener(this::onClick);
        Button weChat = findViewById(R.id.we_chat);
        weChat.setOnClickListener(this::onClick);
        Button myViewPager = findViewById(R.id.my_view_pager);
        myViewPager.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        //数据通信
        if (v.getId() == R.id.btn_data_communication) {
            Intent intent = new Intent(MainActivity.this, DataCommunicationActivity.class);
            startActivity(intent);
            //ViewPager的简单使用
        } else if (v.getId() == R.id.view_pager_simple_used) {
            Intent intent = new Intent(MainActivity.this, ViewPagerAndFragmentActivity.class);
            startActivity(intent);
            //微信左右滑动
        } else if (v.getId() == R.id.we_chat) {
            Intent intent = new Intent(MainActivity.this, WeChatActivity.class);
            startActivity(intent);
            //左右滑动
        } else if (v.getId() == R.id.my_view_pager) {
            Intent intent = new Intent(MainActivity.this, MyPagerViewFragmentActivity.class);
            startActivity(intent);
        }
    }
}