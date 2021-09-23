package com.example.communicationapp.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.communicationapp.R;

import androidx.fragment.app.Fragment;

public class LoginUtil {

    public static boolean isLogin(Context context){
        if(!StringUtils.isEmpty(getUserName(context))){
            return true;
        }
        return false;
    }

    public static String getUserName(Context context){
        String username = "";
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.login_key), Context.MODE_PRIVATE);
        if(sharedPreferences != null){
            username = sharedPreferences.getString(context.getResources().getString(R.string.username_key), "");
        }
        return username;
    }
}
