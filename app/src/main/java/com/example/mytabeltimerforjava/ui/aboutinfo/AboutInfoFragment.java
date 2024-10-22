package com.example.mytabeltimerforjava.ui.aboutinfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        //webView.loadUrl("https://space.bilibili.com/52585240");
        webView.loadUrl("http://zhaoxuyang.cloud");

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
