package com.example.mytabeltimerforjava.ui.morefunc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mytabeltimerforjava.databinding.FragmentMoreFuncBinding;

public class MoreFuncFragment extends Fragment {

    private FragmentMoreFuncBinding binding;
    private MoreFuncViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel = new ViewModelProvider(this).get(MoreFuncViewModel.class);
        binding = FragmentMoreFuncBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // 设置按钮点击事件
        setupButtonClickListeners();

        return root;
    }

    private void setupButtonClickListeners() {
        binding.themeButton1.setOnClickListener(v -> onButtonClicked("功能1"));
        binding.themeButton2.setOnClickListener(v -> onButtonClicked("功能2"));
        binding.themeButton3.setOnClickListener(v -> onButtonClicked("功能3"));
        binding.themeButton4.setOnClickListener(v -> onButtonClicked("功能4"));
    }

    private void onButtonClicked(String functionName) {
        switch (functionName) {
            case "功能1":
                // 执行功能1的逻辑
                performFunction1();
                break;
            case "功能2":
                // 执行功能2的逻辑
                performFunction2();
                break;
            case "功能3":
                // 执行功能3的逻辑
                performFunction3();
                break;
            case "功能4":
                // 执行功能4的逻辑
                performFunction4();
                break;
            default:
                Toast.makeText(getContext(), "未知功能", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void performFunction1() {
        // 功能1的具体实现
        Toast.makeText(getContext(), "执行功能1", Toast.LENGTH_SHORT).show();
    }

    private void performFunction2() {
        // 功能2的具体实现
        Toast.makeText(getContext(), "执行功能2", Toast.LENGTH_SHORT).show();
    }

    private void performFunction3() {
        // 功能3的具体实现
        Toast.makeText(getContext(), "执行功能3", Toast.LENGTH_SHORT).show();
    }

    private void performFunction4() {
        // 功能4的具体实现
        Toast.makeText(getContext(), "执行功能4", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
