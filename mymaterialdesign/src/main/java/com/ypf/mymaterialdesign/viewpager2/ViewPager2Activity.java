package com.ypf.mymaterialdesign.viewpager2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.ypf.mymaterialdesign.databinding.ActivityViewPager2Binding;
import com.ypf.mymaterialdesign.viewpager2.adapter.MyFragmentAdapter;
import com.ypf.mymaterialdesign.viewpager2.fragment.OneFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPager2Activity extends AppCompatActivity {

    private ActivityViewPager2Binding binding;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewPager2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.collapsingToolbarLayout.setTitle("ViewPager2");

        binding.appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    binding.collapsingToolbarLayout.setTitle("ViewPager2");
                } else {
                    binding.collapsingToolbarLayout.setTitle("");
                }
            }
        });
        binding.viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        fragmentList = new ArrayList<>();
        fragmentList.add(OneFragment.newInstance());
        List<String> titles = new ArrayList<>();
        titles.add("OneFragment");

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

