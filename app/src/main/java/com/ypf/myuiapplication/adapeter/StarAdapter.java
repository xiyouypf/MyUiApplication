package com.ypf.myuiapplication.adapeter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ypf.myuiapplication.R;
import com.ypf.myuiapplication.po.Star;
import java.util.List;

public class StarAdapter extends RecyclerView.Adapter<StarAdapter.ViewHolder> {
    private Context context;
    private List<Star> starList;

    public StarAdapter(Context context, List<Star> starList) {
        this.context = context;
        this.starList = starList;
    }

    //是否是组的第一个item
    public boolean isGroupHeader(int position) {
        if (position == 0) {
            return true;
        } else {
            if (getGroupName(position).equals(getGroupName(position - 1))) {
                return false;
            }
            return true;
        }
    }

    //获取组名
    public String getGroupName(int position) {
        return starList.get(position).getGroupName();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_star, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv.setText(starList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return starList == null ? 0 : starList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_star);
        }
    }
}
