package com.example.communicationapp;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.communicationapp.http.Position;
import com.example.communicationapp.http.PositionService;
import com.example.communicationapp.service.GetPositionService;
import com.example.communicationapp.util.HttpServiceCreator;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button btnRequest;
    final PositionService positionService = HttpServiceCreator.create(PositionService.class);
    private String mAndroidId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRequest = findViewById(R.id.btn_request);
//        btnRequest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                JSONObject object=new JSONObject();
//                try {
//                    object.put("latitude", 1d);
//                    object.put("longitude", 2);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), object.toString());
//                positionService.insertPosition(new Position(1,2)).enqueue(new Callback<Position>() {
//                    @Override
//                    public void onResponse(Call<Position> call, Response<Position> response) {
//                        Position position = response.body();
//                        if(position != null){
//                            Log.d("lwd", "发送数据成功 latitude：" + position.latitude + " longitude:" + position.longitude);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Position> call, Throwable t) {
//                        Log.d("lwd", t.getMessage());
//
//                    }
//                });
//
//            }
//        });

        mAndroidId = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        Log.d("lwd", "mAndroidId:" + mAndroidId);

        Intent intent = new Intent(this, GetPositionService.class);
        startService(intent);

    }
}
