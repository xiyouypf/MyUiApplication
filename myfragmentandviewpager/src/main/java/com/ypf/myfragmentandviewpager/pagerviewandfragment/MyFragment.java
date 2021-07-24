package com.ypf.myfragmentandviewpager.pagerviewandfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ypf.myfragmentandviewpager.R;
import com.ypf.myfragmentandviewpager.pagerviewandfragment.base.LazyFragment1;

public class MyFragment extends LazyFragment1 {
    private static final String TAG = "MyFragment";
    private static final String TAB_INDEX = "tab_index";
    private View rootView;
    private int tableIdx;
    ImageView imageView;
    TextView textView;

    public MyFragment() {
    }

    public static MyFragment newInstance(int itemIndex) {
        MyFragment fragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TAB_INDEX, itemIndex);
        fragment.setArguments(bundle);
        return fragment;
    }

//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        tableIdx = getArguments().getInt(TAB_INDEX);
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_my, container, false);
        }
        imageView = rootView.findViewById(R.id.iv_content);
        textView = rootView.findViewById(R.id.tv_loading);
        return rootView;
    }

    @Override
    public int getResource() {
        return R.layout.fragment_my;
    }

    @Override
    public void initView(View rootView) {
        imageView = rootView.findViewById(R.id.iv_content);
        textView = rootView.findViewById(R.id.tv_loading);
        tableIdx = getArguments().getInt(TAB_INDEX);
    }

    @Override
    public void onFragmentLoadStop() {
        super.onFragmentLoadStop();
        tableIdx = getArguments().getInt(TAB_INDEX);

    }
}