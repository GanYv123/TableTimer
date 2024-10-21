package com.example.mytabeltimerforjava.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mytabeltimerforjava.ui.sql.DatabaseHelper;

import java.util.List;

public class HomeViewModel extends ViewModel {
    private final DatabaseHelper dbHelper;
    private final MutableLiveData<List<Course>> courseListLiveData;

    public HomeViewModel(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
        this.courseListLiveData = new MutableLiveData<>();
    }

    public LiveData<List<Course>> getCourseList() {
        return courseListLiveData;
    }

    public void updateCourseList() {
        List<Course> courses = dbHelper.getAllCourses();
        courseListLiveData.setValue(courses);
    }
}
