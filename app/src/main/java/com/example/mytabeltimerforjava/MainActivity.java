package com.example.mytabeltimerforjava;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.mytabeltimerforjava.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding; // 使用 ViewBinding 进行布局绑定

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 加载用户保存的主题
        String themeName = loadThemePreference();
        switch (themeName) {
            case "AppTheme.Gray":
                setTheme(R.style.AppTheme_Gray);
                break;
            case "AppTheme.Purple":
                setTheme(R.style.AppTheme_Purple);
                break;
            case "AppTheme.cream":
                setTheme(R.style.AppTheme_cream);
                break;
            case "AppTheme.EVA01":
                setTheme(R.style.AppTheme_EVA01);
                break;
            default:
                setTheme(R.style.AppTheme_Blue);
                break;
        }

        super.onCreate(savedInstanceState);

        // 设置窗口属性以实现透明状态栏
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS); // 使内容延伸到状态栏

        // 获取当前主题的状态栏颜色
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(android.R.attr.statusBarColor, typedValue, true);
        window.setStatusBarColor(typedValue.data); // 使用 statusBarColor 作为状态栏颜色

        // 初始化 ViewBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 设置 Toolbar 为 ActionBar
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar); // 这一行很重要

        // 获取 BottomNavigationView
        BottomNavigationView navView = binding.navView; // 使用绑定对象

        // 设置导航控制器
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_aboutInfo, R.id.navigation_moreFunc, R.id.navigation_choose_style)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    // 加载主题偏好设置
    private String loadThemePreference() {
        SharedPreferences prefs = getSharedPreferences("theme_prefs", MODE_PRIVATE);
        return prefs.getString("theme", "AppTheme.Blue"); // 默认主题为蓝色主题
    }

    // 切换主题的函数
    private void switchTheme(String newTheme) {
        SharedPreferences prefs = getSharedPreferences("theme_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("theme", newTheme);
        editor.apply();

        recreate(); // 重新创建活动以应用新的主题
    }
}
