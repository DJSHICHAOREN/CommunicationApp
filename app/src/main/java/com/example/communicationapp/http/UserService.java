package com.example.communicationapp.http;

import com.example.communicationapp.entity.SubmitUserResult;
import com.example.communicationapp.entity.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {

    @POST("user")
    Call<SubmitUserResult> addUser(@Body User user);
}
