package com.ypf.myuiapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.LinearLayout;
import com.ypf.myuiapplication.R;
import com.ypf.myuiapplication.adapeter.CustomAdapter;
import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewRecycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recycler_view_recycle);

        RecyclerView rv = findViewById(R.id.rv);
        rv.setLayoutManager(new GridLayoutManager(this, 5));
        rv.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(i + "");
        }
        CustomAdapter adapter = new CustomAdapter(this, list);
        rv.setAdapter(adapter);
    }
}