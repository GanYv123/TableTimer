package com.example.mytabeltimerforjava;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.example.mytabeltimerforjava.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding; // 使用 ViewBinding 进行布局绑定
    private static final String TAG = "MainActivity"; // 日志标签

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 加载用户保存的主题
        String themeName = loadThemePreference();
        Log.d(TAG, "Loaded theme preference: " + themeName); // 日志输出加载的主题

        switch (themeName) {
            case "AppTheme.Gray":
                setTheme(R.style.AppTheme_Gray);
                break;
            case "AppTheme.Pink":
                setTheme(R.style.AppTheme_Pink);
                break;
            case "AppTheme.cream":
                setTheme(R.style.AppTheme_cream);
                break;
            case "AppTheme.EVA01":
                setTheme(R.style.AppTheme_EVA01);
                break;
            default:
                setTheme(R.style.AppTheme_Blue);
                Log.d(TAG, "Default theme set to: AppTheme.Blue");
                break;
        }
        super.onCreate(savedInstanceState); // 只调用一次
        // 初始化 ViewBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.d(TAG, "onCreate called");

        // 加载用户保存的主题
        Log.d(TAG, "Loaded theme preference: " + themeName); // 日志输出加载的主题

        setBackgroundImage(themeName);

        // 设置窗口属性以实现透明状态栏
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS); // 使内容延伸到状态栏

        // 获取当前主题的状态栏颜色
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(android.R.attr.statusBarColor, typedValue, true);
        window.setStatusBarColor(typedValue.data); // 使用 statusBarColor 作为状态栏颜色
        Log.d(TAG, "Status bar color set to: " + typedValue.data); // 日志输出状态栏颜色

        // 设置 Toolbar 为 ActionBar
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar); // 这一行很重要
        Log.d(TAG, "Toolbar set as ActionBar");

        // 获取 BottomNavigationView
        BottomNavigationView navView = binding.navView; // 使用绑定对象

        // 设置导航控制器
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_aboutInfo, R.id.navigation_moreFunc, R.id.navigation_choose_style)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        Log.d(TAG, "Navigation UI setup complete");
    }
    // 加载主题偏好设置
    private String loadThemePreference() {
        SharedPreferences prefs = getSharedPreferences("theme_prefs", MODE_PRIVATE);
        String theme = prefs.getString("theme", "AppTheme.Blue"); // 默认主题为蓝色主题
        Log.d(TAG, "Theme preference retrieved: " + theme);
        return theme;
    }

    // 切换主题的函数
    private void switchTheme(String newTheme) {
        Log.d(TAG, "Switching theme to: " + newTheme);
        SharedPreferences prefs = getSharedPreferences("theme_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("theme", newTheme);
        editor.apply();

        recreate(); // 重新创建活动以应用新的主题
        Log.d(TAG, "Activity recreated to apply new theme");
    }
    private void setBackgroundImage(String themeName) {
        int[] screenResolution = getScreenResolution();
        int width = screenResolution[0];
        int height = screenResolution[1];

        int backgroundResId;
        switch (themeName) {
            case "AppTheme.Gray":
                backgroundResId = R.drawable.background_1;
                break;
            case "AppTheme.Pink":
                backgroundResId = R.drawable.background_2;
                break;
            case "AppTheme.cream":
                backgroundResId = R.drawable.background_4;
                break;
            case "AppTheme.EVA01":
                backgroundResId = R.drawable.background_7;
                break;
            default:
                backgroundResId = R.drawable.background_home_image;
                break;
        }

        // 使用 Glide 加载背景图
        Glide.with(this)
                .load(backgroundResId)
                .override(width, height) // 使用当前设备的分辨率
                .into(binding.backgroundImage);
    }
    // 获取屏幕分辨率
    private int[] getScreenResolution() {
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        Display display = wm.getDefaultDisplay();
        display.getMetrics(metrics);

        return new int[]{metrics.widthPixels, metrics.heightPixels};
    }
}

