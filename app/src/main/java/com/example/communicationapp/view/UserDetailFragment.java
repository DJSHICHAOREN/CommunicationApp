package com.example.communicationapp.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.communicationapp.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class UserDetailFragment extends Fragment {

    private TextView tv_content;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_detail, container, false);
        tv_content = view.findViewById(R.id.tv_content);

        Bundle arguments = getArguments();
        if(arguments != null){
            String username = arguments.getString(getContext().getResources().getString(R.string.username_key));
            tv_content.setText("Hello " + username);
        }
        return view;
    }

    public static UserDetailFragment newInstance(Bundle args){
        UserDetailFragment userDetailFragment = new UserDetailFragment();
        userDetailFragment.setArguments(args);
        return userDetailFragment;
    }
}
