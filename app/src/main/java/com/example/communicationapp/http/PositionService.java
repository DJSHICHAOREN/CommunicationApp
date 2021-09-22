package com.example.communicationapp.http;

import com.example.communicationapp.entity.Position;
import com.example.communicationapp.entity.SubmitPositionParam;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SubmitPositionService {
    @POST("position_device")
    Call<Position> submitPosition(@Body SubmitPositionParam submitPositionParam);
}
