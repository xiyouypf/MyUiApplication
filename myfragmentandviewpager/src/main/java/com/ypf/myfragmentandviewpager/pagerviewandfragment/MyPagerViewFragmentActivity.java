package com.ypf.myfragmentandviewpager.pagerviewandfragment;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ypf.myfragmentandviewpager.R;

import java.util.ArrayList;
import java.util.List;

public class MyPagerViewFragmentActivity extends AppCompatActivity {

    private BottomNavigationView navigationView;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_view_fragment);

        navigationView = findViewById(R.id.bottomNavigationView);
        navigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new MyFragmentPageAdapter(getSupportFragmentManager(), getShowData()));
        viewPager.setOffscreenPageLimit(1);
        viewPager.setOnPageChangeListener(pageChangeListener);
    }

    // 展示五个Fragment
    private List<Fragment> getShowData() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(MyFragment.newInstance(1));
        fragmentList.add(MyFragment.newInstance(2));
        fragmentList.add(MyFragment.newInstance(3));
        fragmentList.add(MyFragment.newInstance(4));
        fragmentList.add(MyFragment.newInstance(5));
        return fragmentList;
    }

    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            int itemId = R.id.fragment1;
            if (position == 0) {
                itemId = R.id.fragment1;
            } else if (position == 1) {
                itemId = R.id.fragment2;
            } else if (position == 2) {
                itemId = R.id.fragment3;
            } else if (position == 3) {
                itemId = R.id.fragment4;
            } else if (position == 4) {
                itemId = R.id.fragment5;
            }
            navigationView.setSelectedItemId(itemId);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if (item.getItemId() == R.id.fragment1) {
                viewPager.setCurrentItem(0, true);
                return true;
            } else if (item.getItemId() == R.id.fragment2) {
                viewPager.setCurrentItem(1, true);
                return true;
            } else if (item.getItemId() == R.id.fragment3) {
                viewPager.setCurrentItem(2, true);
                return true;
            } else if (item.getItemId() == R.id.fragment4) {
                viewPager.setCurrentItem(3, true);
                return true;
            } else if (item.getItemId() == R.id.fragment5) {
                viewPager.setCurrentItem(4, true);
                return true;
            }
            return false;
        }
    };
}