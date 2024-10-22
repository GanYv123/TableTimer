package com.example.mytabeltimerforjava;

import static android.widget.Toast.LENGTH_SHORT;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    private Button skipButton;
    private ImageView gifImageView; // 添加 ImageView 变量
    private CountDownTimer countDownTimer;
    private static final long COUNTDOWN_TIME = 5000; // 5秒倒计时
    private static final long INTERVAL = 1000; // 每秒更新一次

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        skipButton = findViewById(R.id.skipButton);
        gifImageView = findViewById(R.id.gifImageView); // 获取 ImageView

        // 使用 Glide 加载 GIF
        Glide.with(this)
                .load(R.drawable.splash_animation) // 替换为你的 GIF 资源
                .into(gifImageView);

        // 启动倒计时
        startCountdown();

        // 设置跳过按钮的点击事件
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skipToMainActivity();
            }
        });
    }

    private void startCountdown() {
        countDownTimer = new CountDownTimer(COUNTDOWN_TIME, INTERVAL) {
            int remainingTime = 5; // 倒计时开始的秒数

            @Override
            public void onTick(long millisUntilFinished) {
                skipButton.setText("跳过 " + remainingTime + "s");
                remainingTime--;
            }

            @Override
            public void onFinish() {
                skipToMainActivity();
            }
        }.start();
    }

    private void skipToMainActivity() {
        // 跳转到主页
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish(); // 结束当前 Activity
        // 在你的 Activity 或 Fragment 中
        Toast.makeText(this, "跳过成功!", LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 释放计时器资源
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
