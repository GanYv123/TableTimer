package com.example.mytabeltimerforjava.ui.chooseStyle;

import android.app.Application;
import android.content.SharedPreferences;
import androidx.lifecycle.AndroidViewModel;

public class ChooseStyleViewModel extends AndroidViewModel {
    private static final String PREFS_NAME = "theme_prefs";
    private static final String KEY_THEME = "selected_theme";

    public ChooseStyleViewModel(Application application) {
        super(application);
    }

    // 保存选择的主题
    public void selectTheme(String themeName) {
        SharedPreferences prefs = getApplication().getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_THEME, themeName);
        editor.apply();
    }

    // 获取保存的主题
    public String getSelectedTheme() {
        SharedPreferences prefs = getApplication().getSharedPreferences(PREFS_NAME, 0);
        return prefs.getString(KEY_THEME, "AppTheme.Blue"); // 默认主题
    }
}