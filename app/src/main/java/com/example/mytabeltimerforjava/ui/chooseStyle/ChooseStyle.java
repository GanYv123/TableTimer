package com.example.mytabeltimerforjava.ui.chooseStyle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.mytabeltimerforjava.MainActivity;
import com.example.mytabeltimerforjava.R;

public class ChooseStyle extends Fragment {

    private ChooseStyleViewModel chooseStyleViewModel;

    public ChooseStyle() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chooseStyleViewModel = new ViewModelProvider(this).get(ChooseStyleViewModel.class);
        Log.d("ChooseStyle", "Fragment created");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_style, container, false);

        // 初始化按钮并设置点击事件
        Button themeButton1 = view.findViewById(R.id.themeButton1);
        themeButton1.setOnClickListener(v -> saveThemePreferenceAndRestart("AppTheme.Blue", "选择了清新蓝色主题"));

        Button themeButton2 = view.findViewById(R.id.themeButton2);
        themeButton2.setOnClickListener(v -> saveThemePreferenceAndRestart("AppTheme.Gray", "选择了现代灰色主题"));

        Button themeButton3 = view.findViewById(R.id.themeButton3);
        themeButton3.setOnClickListener(v -> saveThemePreferenceAndRestart("AppTheme.Purple", "选择了时尚紫色主题"));

        Button themeButton4 = view.findViewById(R.id.themeButton4);
        themeButton4.setOnClickListener(v -> saveThemePreferenceAndRestart("AppTheme.cream", "选择了冰激凌主题"));

        Button themeButton5 = view.findViewById(R.id.themeButton5);
        themeButton5.setOnClickListener(v -> saveThemePreferenceAndRestart("AppTheme.EVA01", "选择了EVA初号机主题"));

        return view;
    }

    // 保存主题偏好并重启活动以应用更改
    private void saveThemePreferenceAndRestart(String themeName, String message) {
        // 保存到 SharedPreferences
        SharedPreferences prefs = requireActivity().getSharedPreferences("theme_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("theme", themeName);
        editor.apply();

        // 显示 Toast 提示
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

        // 重启 MainActivity 以应用主题
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        requireActivity().finish(); // 关闭当前活动
    }
}
