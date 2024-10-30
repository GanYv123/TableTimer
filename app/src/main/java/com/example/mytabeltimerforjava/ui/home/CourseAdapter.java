package com.example.mytabeltimerforjava.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mytabeltimerforjava.R;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {
    private List<Course> courseList;
    private OnItemLongClickListener longClickListener;
    private int[] rainbowColors = {
            0x886E86D6,
            0x8838E05D,
            0x88FFD700
    };

    public interface OnItemLongClickListener {
        void onItemLongClick(Course course);
        void onItemDeleteClick(Course course); // 添加删除点击接口
    }

    public CourseAdapter(List<Course> courseList, OnItemLongClickListener longClickListener) {
        this.courseList = courseList;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Course course = courseList.get(position);
        holder.textViewName.setText(course.getName());
        holder.textViewTime.setText(course.getTime());
        holder.textViewRoom.setText(course.getRoomName());

        // 设置半透明背景颜色
        int colorIndex = position % rainbowColors.length; // 获取颜色索引
        holder.itemView.setBackgroundColor(rainbowColors[colorIndex]); // 设置颜色

        // 设置不透明文本颜色
        holder.textViewName.setTextColor(0xFF000000); // 黑色
        holder.textViewTime.setTextColor(0xFF000000); // 黑色
        holder.textViewRoom.setTextColor(0xFF000000); // 黑色

        holder.itemView.setOnLongClickListener(v -> {
            longClickListener.onItemLongClick(course);
            return true;
        });

        // 添加删除点击事件
        holder.itemView.setOnClickListener(v -> longClickListener.onItemDeleteClick(course));
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public void updateCourseList(List<Course> courses) {
        this.courseList = courses;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewTime;
        TextView textViewRoom;

        ViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewTime = itemView.findViewById(R.id.text_view_time);
            textViewRoom = itemView.findViewById(R.id.text_view_room_name);
        }
    }
}
