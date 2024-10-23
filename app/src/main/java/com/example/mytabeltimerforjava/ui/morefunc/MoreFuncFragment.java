package com.example.mytabeltimerforjava.ui.morefunc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mytabeltimerforjava.databinding.FragmentMoreFuncBinding;

public class MoreFuncFragment extends Fragment {

    private FragmentMoreFuncBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MoreFuncViewModel notificationsViewModel =
                new ViewModelProvider(this).get(MoreFuncViewModel.class);

        binding = FragmentMoreFuncBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textNotifications;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        textView.setText("11111111");
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}