package com.example.mytabeltimerforjava.ui.chooseStyle;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button; // 引入需要的 UI 元素
import android.widget.Toast; // 引入 Toast
import com.example.mytabeltimerforjava.R;

public class ChooseStyle extends Fragment {

    private ChooseStyleViewModel chooseStyleViewModel; // 声明 ViewModel

    public ChooseStyle() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 获取 ViewModel 的实例
        chooseStyleViewModel = new ViewModelProvider(this).get(ChooseStyleViewModel.class);
        Log.d("ChooseStyle", "Fragment created");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_choose_style, container, false);

        // 获取 UI 元素并设置点击监听
        Button themeButton1 = view.findViewById(R.id.themeButton1);
        themeButton1.setOnClickListener(v -> {
            chooseStyleViewModel.selectTheme("Theme1"); // 选择主题
            Toast.makeText(getActivity(), "选择了清新蓝色主题", Toast.LENGTH_SHORT).show(); // 显示 Toast
        });

        Button themeButton2 = view.findViewById(R.id.themeButton2);
        themeButton2.setOnClickListener(v -> {
            chooseStyleViewModel.selectTheme("Theme2"); // 选择主题
            Toast.makeText(getActivity(), "选择了现代灰色主题", Toast.LENGTH_SHORT).show(); // 显示 Toast
        });

        Button themeButton3 = view.findViewById(R.id.themeButton3);
        themeButton3.setOnClickListener(v -> {
            chooseStyleViewModel.selectTheme("Theme3"); // 选择主题
            Toast.makeText(getActivity(), "选择了时尚紫色主题", Toast.LENGTH_SHORT).show(); // 显示 Toast
        });

        Button themeButton4 = view.findViewById(R.id.themeButton4);
        themeButton4.setOnClickListener(v -> {
            chooseStyleViewModel.selectTheme("IceCreamTheme"); // 选择主题
            Toast.makeText(getActivity(), "选择了冰激凌主题", Toast.LENGTH_SHORT).show(); // 显示 Toast
        });

        Button themeButton5 = view.findViewById(R.id.themeButton5);
        themeButton5.setOnClickListener(v -> {
            chooseStyleViewModel.selectTheme("EVA01"); // 选择主题
            Toast.makeText(getActivity(), "选择了EVA初号机主题", Toast.LENGTH_SHORT).show(); // 显示 Toast
        });

        return view;
    }
}
