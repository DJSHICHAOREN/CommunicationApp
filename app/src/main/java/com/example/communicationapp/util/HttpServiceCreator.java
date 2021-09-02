package com.example.communicationapp.util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpServiceCreator {

    private static Retrofit retrofit = new Retrofit
            .Builder()
            .baseUrl("http://10.252.112.167:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public HttpServiceCreator(){

    }

    public static  <T> T create(Class<T> serviceClass){
        return retrofit.create(serviceClass);
    }


}
