package com.ypf.myfragmentandviewpager.viewpagerandfragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.ypf.myfragmentandviewpager.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder> {
    private List<String> titles = new ArrayList<>();
    private List<Integer> colors = new ArrayList<>();

    public ViewPagerAdapter() {
        titles.add("hello");
        titles.add("kitty");
        titles.add("享学");
        titles.add("课堂");
        titles.add("I");
        titles.add("like");
        titles.add("you");
        titles.add("my");
        titles.add("best");
        titles.add("wish");

        colors.add(R.color.white);
        colors.add(R.color.black);
        colors.add(R.color.red);
        colors.add(R.color.colorAccent);
        colors.add(R.color.colorPrimary);
        colors.add(R.color.white);
        colors.add(R.color.black);
        colors.add(R.color.red);
        colors.add(R.color.colorPrimary);
        colors.add(R.color.white);
    }

    @NonNull
    @Override
    public ViewPagerAdapter.ViewPagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pager, parent, false);
        return new ViewPagerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerAdapter.ViewPagerViewHolder holder, int position) {
        holder.mTv.setText(titles.get(position));
        holder.mContainer.setBackgroundResource(colors.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewPagerViewHolder extends RecyclerView.ViewHolder {
        TextView mTv;
        RelativeLayout mContainer;
        public ViewPagerViewHolder(@NonNull View itemView) {
            super(itemView);
            mContainer = itemView.findViewById(R.id.container);
            mTv = itemView.findViewById(R.id.tvTitle);
        }
    }
}
