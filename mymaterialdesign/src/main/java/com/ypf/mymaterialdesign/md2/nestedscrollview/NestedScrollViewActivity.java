package com.ypf.mymaterialdesign.md2.nestedscrollview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.ypf.mymaterialdesign.R;
import com.ypf.mymaterialdesign.databinding.ActivityNestedScrollViewBinding;
import com.ypf.mymaterialdesign.md2.adapter.MyFragmentAdapter;
import com.ypf.mymaterialdesign.md2.fragment.NestedTestFragment;

import java.util.ArrayList;
import java.util.List;

public class NestedScrollViewActivity extends AppCompatActivity {

    private ActivityNestedScrollViewBinding binding;
    private List<Fragment> fragmentList;
    private List<String> titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNestedScrollViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        fragmentList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            fragmentList.add(NestedTestFragment.newInstance("NestedScrollView"));
        }
        titles = new ArrayList<>();
        titles.add("首页");
        titles.add("商品");
        titles.add("个人");
        titles.add("关于");

        MyFragmentAdapter myFragmentAdapter = new MyFragmentAdapter(this, fragmentList);
        binding.viewPager.setAdapter(myFragmentAdapter);

        new TabLayoutMediator(binding.tab, binding.viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(titles.get(position));
            }
        }).attach();
    }
}