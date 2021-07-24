package com.ypf.mymaterialdesign.floatingactionbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.ypf.mymaterialdesign.databinding.ActivityFloatActionButtonBinding;

public class FloatActionButtonActivity extends AppCompatActivity {

    private ActivityFloatActionButtonBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFloatActionButtonBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Hello floatingactionbutton...", Snackbar.LENGTH_LONG)
                        .setAction("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(FloatActionButtonActivity.this, "取消", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });
    }
}