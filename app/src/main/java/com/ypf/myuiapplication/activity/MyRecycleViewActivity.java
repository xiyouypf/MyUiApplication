package com.ypf.myuiapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ypf.myuiapplication.R;
import com.ypf.myuiapplication.adapeter.StarAdapter;
import com.ypf.myuiapplication.adapeter.StarDecoration;
import com.ypf.myuiapplication.po.Star;

import java.util.ArrayList;
import java.util.List;

public class MyRecycleViewActivity extends AppCompatActivity {

    private List<Star> starList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recycle_view);

        init();

        RecyclerView recyclerView = findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));//设置布局管理器
        recyclerView.addItemDecoration(new StarDecoration(this));//自定义分割线
//        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));//默认分割线
        recyclerView.setAdapter(new StarAdapter(this, starList));//设置适配器
    }

    private void init() {
        starList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 20; j++) {
                if (i % 2 == 0) {
                    starList.add(new Star("何炅" + j, "快乐家族" + i));
                } else {
                    starList.add(new Star("江寒" + j, "天天兄弟" + i));
                }
            }
        }
    }
}