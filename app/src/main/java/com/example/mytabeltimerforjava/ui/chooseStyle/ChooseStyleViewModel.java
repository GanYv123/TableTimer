package com.example.mytabeltimerforjava.ui.chooseStyle;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.lifecycle.AndroidViewModel;

public class ChooseStyleViewModel extends AndroidViewModel {
    private static final String PREFS_NAME = "theme_prefs";
    private static final String KEY_THEME = "selected_theme";
    private static final String TAG = "ChooseStyleViewModel"; // 日志标签

    public ChooseStyleViewModel(Application application) {
        super(application);
        Log.d(TAG, "ChooseStyleViewModel created");
    }

    // 保存选择的主题
    public void selectTheme(String themeName) {
        SharedPreferences prefs = getApplication().getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_THEME, themeName);
        editor.apply();
        Log.d(TAG, "主题已选择并保存: " + themeName); // 日志输出保存的主题
    }

    // 获取保存的主题
    public String getSelectedTheme() {
        SharedPreferences prefs = getApplication().getSharedPreferences(PREFS_NAME, 0);
        String selectedTheme = prefs.getString(KEY_THEME, "AppTheme.Blue"); // 默认主题
        Log.d(TAG, "获取到的主题: " + selectedTheme); // 日志输出获取的主题
        return selectedTheme;
    }
}
