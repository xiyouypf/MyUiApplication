package com.ypf.myfragmentandviewpager.pagerviewandfragment.base;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class LazyFragment1 extends Fragment {
    private static final String TAG = "LazyFragment1";
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getResource(), container, false);
        }
        initView(rootView);
        return rootView;
    }

    public abstract int getResource();

    public abstract void initView(View rootView);

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            dispatchUserVisibleHint(true);
        } else {
            dispatchUserVisibleHint(false);
        }
    }

    private void dispatchUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            //加载数据
            onFragmentLoad();
        } else {
            //停止一切操作
            onFragmentLoadStop();
        }
    }

    //停止网络数据请求
    public void onFragmentLoadStop() {
        Log.d(TAG, "onFragmentLoadStop: ");
    }

    //加载网络数据请求
    public void onFragmentLoad() {
        Log.d(TAG, "onFragmentLoad: ");
    }
}
