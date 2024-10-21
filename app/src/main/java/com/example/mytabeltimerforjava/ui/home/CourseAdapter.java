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
            0x80FF5733, // 半透明红色
            0x80FFC300, // 半透明黄色
            0x8000FF00, // 半透明绿色
            0x8000FFFF, // 半透明青色
            0xFF5733FF, // 半透明紫色
            0x80FF33FF  // 半透明粉色
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
