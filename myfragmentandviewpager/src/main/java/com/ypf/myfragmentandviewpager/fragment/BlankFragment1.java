package com.ypf.myfragmentandviewpager.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ypf.myfragmentandviewpager.R;

public class BlankFragment1 extends Fragment {
    private static final String TAG = "BlankFragment1";
    private View rootView;

    public BlankFragment1() {
    }

    private IFragmentCallback fragmentCallback;
    public void setCallback(IFragmentCallback callback) {
        this.fragmentCallback = callback;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        String string = bundle.getString("message");
        Log.d(TAG, "onCreate: " + string);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_blank1, container, false);
        }
        Button btn = rootView.findViewById(R.id.btn_3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentCallback.sendMsgToActivity("hello,i am from Fragment");
//                String msg = fragmentCallback.getMsgFromActivity();
//                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }
}