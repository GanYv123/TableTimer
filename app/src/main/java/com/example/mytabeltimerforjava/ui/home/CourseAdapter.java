package com.example.mytabeltimerforjava.ui.home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mytabeltimerforjava.R;
import com.example.mytabeltimerforjava.ui.chooseStyle.ChooseStyleViewModel;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {
    private List<Course> courseList;
    private final OnItemLongClickListener longClickListener;
    private static final String TAG = "CourseAdapter"; // 日志标签
    private final ChooseStyleViewModel chooseStyleViewModel;
    private String currentTheme;

    private int[] EVA_01 = {
            0x886E86D6,
            0x8838E05D,
            0x88FFD700
    };

    public interface OnItemLongClickListener {
        void onItemLongClick(Course course);
        void onItemDeleteClick(Course course); // 添加删除点击接口
    }

    public CourseAdapter(List<Course> courseList, OnItemLongClickListener longClickListener, ChooseStyleViewModel viewModel) {
        this.courseList = courseList;
        this.longClickListener = longClickListener;
        this.chooseStyleViewModel = viewModel;
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

        // 获取当前主题
        currentTheme = chooseStyleViewModel.getSelectedTheme();
        int[] colors;

        // 根据当前主题选择颜色
        switch (currentTheme) {
            case "AppTheme.EVA01":
                colors = EVA_01; // 使用 EVA_01 配色方案
                break;
            case "AppTheme.Gray":
                colors = new int[] {
                        0x88D3D3D3,
                        0x88A9A9A9,
                        0x88808080
                };
                break;
            case "AppTheme.Pink":
                colors = new int[] {
                        0x88E6E6FA,
                        0x88DDA0DD,
                        0x88BA55D3
                };
                break;
            case "AppTheme.cream":
                colors = new int[] {
                        0x88FFFDD0,
                        0x88FFFACD,
                        0x88FFE4B5
                };
                break;
            case "AppTheme.Blue":
            default:
                colors = new int[] {
                        0x8800BFFF,
                        0x8801EFFF,
                        0x88ADD8E6
                };
                break;
        }

        // 设置半透明背景颜色
        int colorIndex = position % colors.length;
        holder.itemView.setBackgroundResource(R.drawable.item_background); // 设置带阴影和描边的背景
        holder.itemView.getBackground().mutate().setTint(colors[colorIndex]); // 设置颜色

        // 设置文本颜色
        holder.textViewName.setTextColor(0xFF000000); // 黑色
        holder.textViewTime.setTextColor(0xFF000000); // 黑色
        holder.textViewRoom.setTextColor(0xFF000000); // 黑色

        // 长按事件
        holder.itemView.setOnLongClickListener(v -> {
            longClickListener.onItemLongClick(course);
            return true;
        });

        // 删除点击事件
        holder.itemView.setOnClickListener(v -> longClickListener.onItemDeleteClick(course));

        // 输出当前主题
        Log.w(TAG, "当前加载的主题: " + currentTheme);
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
