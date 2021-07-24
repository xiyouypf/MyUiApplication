package com.ypf.mymaterialdesign.md2.nestedscrolling;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ypf.mymaterialdesign.databinding.ActivityNestedScrolling1Binding;

public class NestedScrolling1Activity extends AppCompatActivity {

    private ActivityNestedScrolling1Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNestedScrolling1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}