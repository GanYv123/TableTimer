package com.example.mytabeltimerforjava.ui.home;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytabeltimerforjava.databinding.FragmentHomeBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private CourseAdapter courseAdapter;
    private List<Course> courseList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // 初始化 RecyclerView
        recyclerView = binding.recyclerView;

        // 初始化课程列表，替换成实际的数据源
        courseList = new ArrayList<>();
        courseList.add(new Course("高数", "9:00 - 10:00", "B 101", Color.parseColor("#80FF0000"))); // 半透明红色
        courseList.add(new Course("计算机科学", "10:00 - 11:00", "C 102", Color.parseColor("#8000FF00"))); // 半透明绿色
        courseList.add(new Course("软件工程", "11:00 - 12:00", "北理工 103", Color.parseColor("#800000FF"))); // 半透明蓝色

        // 设置 RecyclerView 的布局管理器和适配器
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        courseAdapter = new CourseAdapter(courseList, new CourseAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(Course course) {
                showEditOptionDialog(course); // 弹出选择对话框
            }
        });
        recyclerView.setAdapter(courseAdapter);

        return root;
    }

    // 弹出选择对话框以修改课程名称或时间
    private void showEditOptionDialog(Course course) {
        String[] options = {"修改课程名称", "修改课程时间","修改教室号"};

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext());
        builder.setTitle("选择要修改的内容")
                .setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            showEditNameDialog(course); // 修改课程名称
                        } else if (which == 1) {
                            showEditTimeDialog(course); // 修改课程时间
                        } else if (which == 2) {
                            showEditClassroomDialog(course);//修改教室
                        }
                    }
                })
                .show();
    }

    // 弹出对话框修改课程名称
    private void showEditNameDialog(Course course) {
        EditText inputName = new EditText(getContext());
        inputName.setHint("课程名称");
        inputName.setText(course.getName()); // 设置当前课程名称

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext());
        builder.setTitle("修改课程名称")
                .setView(inputName)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newName = inputName.getText().toString();
                        if (!newName.isEmpty()) {
                            course.setName(newName);
                            courseAdapter.notifyDataSetChanged();
                            Toast.makeText(getContext(), "课程名称已修改", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "课程名称不能为空", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }

    // 弹出对话框修改课程时间
    private void showEditTimeDialog(Course course) {
        EditText inputTime = new EditText(getContext());
        inputTime.setHint("课程时间");
        inputTime.setText(course.getTime()); // 设置当前课程时间

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext());
        builder.setTitle("修改课程时间")
                .setView(inputTime)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newTime = inputTime.getText().toString();
                        if (!newTime.isEmpty()) {
                            course.setTime(newTime);
                            courseAdapter.notifyDataSetChanged();
                            Toast.makeText(getContext(), "课程时间已修改", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "课程时间不能为空", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }

    // 弹出对话框修改课程教室
    private void showEditClassroomDialog(Course course) {
        EditText inputClassroom = new EditText(getContext());
        inputClassroom.setHint("教室");
        inputClassroom.setText(course.getRoomName()); // 设置当前课程教室

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext());
        builder.setTitle("修改教室")
                .setView(inputClassroom)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newClassroom = inputClassroom.getText().toString();
                        if (!newClassroom.isEmpty()) {
                            course.setRoomName(newClassroom);
                            courseAdapter.notifyDataSetChanged();
                            Toast.makeText(getContext(), "教室已修改", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "教室不能为空", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
