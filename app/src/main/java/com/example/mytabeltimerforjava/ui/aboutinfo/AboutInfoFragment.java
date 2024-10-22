package com.example.mytabeltimerforjava.ui.aboutinfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mytabeltimerforjava.databinding.FragmentAboutInfosBinding;

public class AboutInfoFragment extends Fragment {

    private FragmentAboutInfosBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AboutInfoViewModel aboutInfoViewModel =
                new ViewModelProvider(this).get(AboutInfoViewModel.class);

        binding = FragmentAboutInfosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // 获取 WebView 的引用
        WebView webView = binding.webView; // 确保你的布局中 WebView 的 id 是 webView
        webView.setWebViewClient(new WebViewClient()); // 设置 WebViewClient 以在 WebView 内部加载网页
        webView.getSettings().setJavaScriptEnabled(true); // 启用 JavaScript

        // 启用缓存
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.getSettings().setDomStorageEnabled(true); // 启用DOM存储

        // 加载 URL
        webView.loadUrl("http://zhaoxuyang.cloud");

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        // 在这里可以选择重新加载网页或其他逻辑
    }

}
