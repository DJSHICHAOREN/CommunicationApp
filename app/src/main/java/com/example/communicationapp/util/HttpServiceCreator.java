package com.example.communicationapp.util;

import android.util.Log;
import android.view.View;

import com.example.communicationapp.R;
import com.example.communicationapp.User;
import com.example.communicationapp.UserService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpServiceCreator {

    private static Retrofit retrofit = new Retrofit
            .Builder()
            .baseUrl("http://10.252.112.149:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public HttpServiceCreator(){

    }

    public static  <T> T create(Class<T> serviceClass){
        return retrofit.create(serviceClass);
    }


}
