package com.example.communicationapp.http;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface PositionService {

    @POST("position")
    Call<Position> insertPosition(@Body Position position);

}
