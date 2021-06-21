package com.ypf.myfragmentandviewpager.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ypf.myfragmentandviewpager.R;

public class DataCommunicationActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_communication);
        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(this::onClick);
        Button btn2 = findViewById(R.id.btn_2);
        btn2.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn) {
            Bundle bundle = new Bundle();
            bundle.putString("message", "我爱学习");
            BlankFragment1 bf = new BlankFragment1();
            bf.setArguments(bundle);
            bf.setCallback(new IFragmentCallback() {
                @Override
                public void sendMsgToActivity(String msg) {
                    Toast.makeText(DataCommunicationActivity.this, msg, Toast.LENGTH_SHORT).show();
                }

                @Override
                public String getMsgFromActivity() {
                    return "hello,i am from activity";
                }
            });
            replaceFragment(bf);
        } else if (v.getId() == R.id.btn_2) {
            replaceFragment(new ItemFragment());
        }
    }

    //动态切换Fragment
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.framelayout, fragment);
//        transaction.addToBackStack(null);
        transaction.commit();
    }
}