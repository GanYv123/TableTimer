package com.example.mytabeltimerforjava.ui.aboutinfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AboutInfoViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AboutInfoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}