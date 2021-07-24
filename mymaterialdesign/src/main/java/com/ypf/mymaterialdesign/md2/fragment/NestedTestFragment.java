package com.ypf.mymaterialdesign.md2.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ypf.mymaterialdesign.R;
import com.ypf.mymaterialdesign.comm.CommonRecyclerAdapter;
import com.ypf.mymaterialdesign.comm.CommonRecyclerHolder;
import com.ypf.mymaterialdesign.databinding.FragmentOneBinding;

import java.util.ArrayList;
import java.util.List;

public class NestedTestFragment extends Fragment {

    private FragmentOneBinding binding;
    private String mText;

    public static NestedTestFragment newInstance(String text) {
        NestedTestFragment fragment = new NestedTestFragment();
        Bundle bundle = new Bundle();
        bundle.putString("text", text);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOneBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mText = getArguments().getString("text", "");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        binding.recyclerView.setLayoutManager(linearLayoutManager);

        CommonRecyclerAdapter<String> commonRecyclerAdapter = new CommonRecyclerAdapter<String>(requireContext(), createData(), R.layout.item_recycle) {
            @Override
            public void convert(CommonRecyclerHolder holder, String item, int position, boolean isScrolling) {
                holder.setText(R.id.item_tv, item);
            }
        };
        binding.recyclerView.setAdapter(commonRecyclerAdapter);
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
    }

    private List<String> createData() {
        List<String> result = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            result.add(mText + i);
        }
        return result;
    }
}