package com.example.mytabeltimerforjava.ui.morefunc;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MoreFuncViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MoreFuncViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("更多功能待添加!");
    }

    public LiveData<String> getText() {
        return mText;
    }

    // 此处添加方法来处理按钮点击事件的逻辑
    public void onFunctionSelected(String function) {
        // 在这里处理不同功能的逻辑
        mText.setValue(function + " 被点击了!");
    }
}
