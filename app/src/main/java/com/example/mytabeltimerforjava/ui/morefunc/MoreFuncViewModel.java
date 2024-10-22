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
}