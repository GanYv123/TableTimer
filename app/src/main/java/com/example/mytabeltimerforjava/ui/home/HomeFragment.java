package com.example.mytabeltimerforjava.ui.home;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytabeltimerforjava.R;
import com.example.mytabeltimerforjava.databinding.FragmentHomeBinding;
import com.example.mytabeltimerforjava.ui.sql.DatabaseHelper;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private CourseAdapter courseAdapter;
    private HomeViewModel homeViewModel;
    private DatabaseHelper dbHelper;
    private List<Course> courseList;

    @SuppressWarnings("unchecked")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // 初始化数据库帮助类
        dbHelper = new DatabaseHelper(getContext());

        // 初始化课程列表
        courseList = new ArrayList<>();

        // 初始化 ViewModel
        homeViewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new HomeViewModel(dbHelper);
            }
        }).get(HomeViewModel.class);

        // 初始化 RecyclerView
        recyclerView = binding.recyclerView;
        courseAdapter = new CourseAdapter(courseList, new CourseAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(Course course) {
                showEditOptionDialog(course);
            }

            @Override
            public void onItemDeleteClick(Course course) {
                showDeleteConfirmationDialog(course);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(courseAdapter);

        // 观察课程数据变化
        homeViewModel.getCourseList().observe(getViewLifecycleOwner(), courses -> {
            courseAdapter.updateCourseList(courses);
        });

        // 初始化课程列表
        homeViewModel.updateCourseList();

        // 添加课程按钮逻辑
        binding.addCourseButton.setOnClickListener(v -> showAddCourseDialog());

        return root;
    }

    private void showEditOptionDialog(Course course) {
        String[] options = {"修改课程名称", "修改课程时间", "修改教室号"};

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext());
        builder.setTitle("选择要修改的内容")
                .setItems(options, (dialog, which) -> {
                    if (which == 0) {
                        showEditNameDialog(course);
                    } else if (which == 1) {
                        showEditTimeDialog(course);
                    } else if (which == 2) {
                        showEditClassroomDialog(course);
                    }
                })
                .show();
    }

    private void showEditNameDialog(Course course) {
        EditText inputName = new EditText(getContext());
        inputName.setHint("课程名称");
        inputName.setText(course.getName());

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext());
        builder.setTitle("修改课程名称")
                .setView(inputName)
                .setPositiveButton("确定", (dialog, which) -> {
                    String newName = inputName.getText().toString();
                    if (!newName.isEmpty()) {
                        course.setName(newName);
                        dbHelper.updateCourse(course.getId(), newName, course.getTime(), course.getRoomName());
                        homeViewModel.updateCourseList();
                        Toast.makeText(getContext(), "课程名称已修改", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "课程名称不能为空", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", (dialog, which) -> dialog.cancel())
                .show();
    }
    private void showEditTimeDialog(Course course) {
        // 使用 XML 布局文件
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_select_time, null);

        // 从布局中获取 NumberPickers
        final NumberPicker startHourPicker = dialogView.findViewById(R.id.start_hour_picker);
        final NumberPicker startMinutePicker = dialogView.findViewById(R.id.start_minute_picker);
        final NumberPicker endHourPicker = dialogView.findViewById(R.id.end_hour_picker);
        final NumberPicker endMinutePicker = dialogView.findViewById(R.id.end_minute_picker);

        // 设置 NumberPicker 的范围
        startHourPicker.setMinValue(1); // 1-12
        startHourPicker.setMaxValue(12);
        startMinutePicker.setMinValue(0);
        startMinutePicker.setMaxValue(11); // 每 5 分钟为刻度
        startMinutePicker.setFormatter(value -> String.format("%02d", value * 5));

        endHourPicker.setMinValue(1); // 1-12
        endHourPicker.setMaxValue(12);
        endMinutePicker.setMinValue(0);
        endMinutePicker.setMaxValue(11); // 每 5 分钟为刻度
        endMinutePicker.setFormatter(value -> String.format("%02d", value * 5));

        // 解析当前课程时间并设置初始值
        String[] timeParts = course.getTime().split("-");
        if (timeParts.length == 2) {
            String[] startParts = timeParts[0].split(":");
            String[] endParts = timeParts[1].split(":");
            if (startParts.length == 2) {
                int startHour = Integer.parseInt(startParts[0]) % 12;
                int startMinute = Integer.parseInt(startParts[1]) / 5;
                startHourPicker.setValue(startHour == 0 ? 12 : startHour);
                startMinutePicker.setValue(startMinute);
            }
            if (endParts.length == 2) {
                int endHour = Integer.parseInt(endParts[0]) % 12;
                int endMinute = Integer.parseInt(endParts[1]) / 5;
                endHourPicker.setValue(endHour == 0 ? 12 : endHour);
                endMinutePicker.setValue(endMinute);
            }
        }

        // 创建对话框
        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("选择课程时间")
                .setView(dialogView)
                .setPositiveButton("确定", (dialog, which) -> {
                    // 获取用户选择的开始时间
                    int startHour = startHourPicker.getValue();
                    int startMinute = startMinutePicker.getValue() * 5;
                    String startTime = String.format("%02d:%02d", startHour % 12, startMinute);

                    // 获取用户选择的结束时间
                    int endHour = endHourPicker.getValue();
                    int endMinute = endMinutePicker.getValue() * 5;
                    String endTime = String.format("%02d:%02d", endHour % 12, endMinute);

                    // 更新课程时间并保存
                    course.setTime(startTime + "-" + endTime);
                    dbHelper.updateCourse(course.getId(), course.getName(), startTime + "-" + endTime, course.getRoomName());
                    homeViewModel.updateCourseList();
                    Toast.makeText(getContext(), "课程时间已修改", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("取消", (dialog, which) -> dialog.cancel())
                .show();
    }


    private void showEditClassroomDialog(Course course) {
        EditText inputClassroom = new EditText(getContext());
        inputClassroom.setHint("教室");
        inputClassroom.setText(course.getRoomName());

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext());
        builder.setTitle("修改教室")
                .setView(inputClassroom)
                .setPositiveButton("确定", (dialog, which) -> {
                    String newClassroom = inputClassroom.getText().toString();
                    if (!newClassroom.isEmpty()) {
                        course.setRoomName(newClassroom);
                        dbHelper.updateCourse(course.getId(), course.getName(), course.getTime(), newClassroom);
                        homeViewModel.updateCourseList();
                        Toast.makeText(getContext(), "教室已修改", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "教室不能为空", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", (dialog, which) -> dialog.cancel())
                .show();
    }


    private void showAddCourseDialog() {
        // 创建一个对话框的视图
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_course, null);
        EditText inputName = dialogView.findViewById(R.id.edit_course_name);
        EditText inputClassroom = dialogView.findViewById(R.id.edit_course_classroom);
        Button btnSelectTime = dialogView.findViewById(R.id.btn_select_time);

        // 默认时间
        String[] selectedTime = {"02:00", "04:00"};

        // 点击按钮弹出时间选择对话框
        btnSelectTime.setOnClickListener(v -> showTimePickerDialog(selectedTime));

        // 创建对话框
        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("添加课程")
                .setView(dialogView)
                .setPositiveButton("确定", (dialog, which) -> {
                    String name = inputName.getText().toString();
                    String classroom = inputClassroom.getText().toString();

                    if (!name.isEmpty() && !classroom.isEmpty()) {
                        // 使用选择的时间（或默认时间）
                        String time = selectedTime[0] + "-" + selectedTime[1];

                        // 插入课程并更新视图
                        long id = dbHelper.insertCourse(name, time, classroom);
                        Course newCourse = new Course(name, time, classroom, 0);
                        newCourse.setId((int) id);
                        courseList.add(newCourse);
                        courseAdapter.notifyItemInserted(courseList.size() - 1);
                        homeViewModel.updateCourseList();
                        Toast.makeText(getContext(), "课程已添加", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "请填写所有字段", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", (dialog, which) -> dialog.cancel())
                .show();
    }

    // 时间选择器对话框
    private void showTimePickerDialog(String[] selectedTime) {
        // 获取当前时间
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // 创建开始时间选择器
        TimePickerDialog startTimePicker = new TimePickerDialog(getContext(), (view, selectedHour, selectedMinute) -> {
            String startTime = String.format("%02d:%02d", selectedHour % 12, selectedMinute);
            selectedTime[0] = startTime;

            // 创建结束时间选择器
            TimePickerDialog endTimePicker = new TimePickerDialog(getContext(), (view1, endHour, endMinute) -> {
                String endTime = String.format("%02d:%02d", endHour % 12, endMinute);
                selectedTime[1] = endTime;
            }, hour, minute, true);
            endTimePicker.setTitle("选择结束时间");
            endTimePicker.show();

        }, hour, minute, true);
        startTimePicker.setTitle("选择开始时间");
        startTimePicker.show();
    }


    // 配置时间选择器
    private void configureTimePicker(NumberPicker startHourPicker, NumberPicker startMinutePicker,
                                     NumberPicker endHourPicker, NumberPicker endMinutePicker) {
        startHourPicker.setMinValue(0);
        startHourPicker.setMaxValue(23);
        startMinutePicker.setMinValue(0);
        startMinutePicker.setMaxValue(11);
        startMinutePicker.setFormatter(value -> String.format("%02d", value * 5));

        endHourPicker.setMinValue(0);
        endHourPicker.setMaxValue(23);
        endMinutePicker.setMinValue(0);
        endMinutePicker.setMaxValue(11);
        endMinutePicker.setFormatter(value -> String.format("%02d", value * 5));
    }


    private void showDeleteConfirmationDialog(Course course) {
        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("删除课程")
                .setMessage("确定要删除课程 " + course.getName() + " 吗？")
                .setPositiveButton("删除", (dialog, which) -> {
                    dbHelper.deleteCourse(course.getId());

                    // 从课程列表中移除该课程
                    courseList.remove(course); // 从列表中移除课程
                    courseAdapter.notifyDataSetChanged(); // 通知适配器数据已更改

                    // 更新课程列表，确保数据源与数据库同步
                    homeViewModel.updateCourseList();
                    Toast.makeText(getContext(), "课程已删除", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("取消", (dialog, which) -> dialog.cancel())
                .show();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        dbHelper.close(); // 关闭数据库连接
    }
}
