package com.example.mytabeltimerforjava.ui.chooseStyle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ChooseStyleViewModel extends ViewModel {
    // 创建一个 MutableLiveData，用于存储用户选择的主题
    private final MutableLiveData<String> selectedTheme = new MutableLiveData<>();

    // 方法用于更新主题
    public void selectTheme(String theme) {
        selectedTheme.setValue(theme);
    }

    // 获取 LiveData，以便在 Fragment 中观察
    public LiveData<String> getSelectedTheme() {
        return selectedTheme;
    }
}
