package com.example.mytabeltimerforjava.ui.home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List; // 导入 List

import com.example.mytabeltimerforjava.R;
import com.example.mytabeltimerforjava.ui.home.Course; // 导入 Course 类

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    private List<Course> courseList; // 课程列表
    private OnItemLongClickListener onItemLongClickListener;

    public interface OnItemLongClickListener {
        void onItemLongClick(Course course);
    }

    public CourseAdapter(List<Course> courseList, OnItemLongClickListener listener) {
        this.courseList = courseList;
        this.onItemLongClickListener = listener;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = courseList.get(position);
        Log.d("CourseAdapter", "Binding course: " + course.getName());

        holder.bind(course, onItemLongClickListener);

        // 设置背景颜色
        holder.itemView.setBackgroundColor(course.getColor());
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewTime;
        private TextView textViewRoomName;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewTime = itemView.findViewById(R.id.text_view_time);
            textViewRoomName = itemView.findViewById(R.id.text_view_room_name);
        }

        public void bind(Course course, OnItemLongClickListener listener) {
            textViewName.setText(course.getName());
            textViewTime.setText(course.getTime());
            textViewRoomName.setText(course.getRoomName());

            itemView.setOnLongClickListener(v -> {
                listener.onItemLongClick(course);
                return true;
            });
        }
    }
}
