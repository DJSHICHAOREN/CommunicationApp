package com.example.communicationapp;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.communicationapp.entity.SubmitUserResult;
import com.example.communicationapp.entity.User;
import com.example.communicationapp.http.UserService;
import com.example.communicationapp.service.GetPositionService;
import com.example.communicationapp.util.HttpServiceCreator;

public class MainActivity extends AppCompatActivity {

    private EditText et_username;
    private EditText et_password;
    private Button btn_submit;
    final UserService userService = HttpServiceCreator.create(UserService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        btn_submit = findViewById(R.id.btn_submit);

        Intent intent = new Intent(this, GetPositionService.class);
        startService(intent);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                User user = new User(username, password);
                userService.addUser(user).enqueue(new Callback<SubmitUserResult>() {
                    @Override
                    public void onResponse(Call<SubmitUserResult> call, Response<SubmitUserResult> response) {
                        SubmitUserResult submitUserResult = response.body();
                        if(submitUserResult != null){
                            Toast.makeText(getApplicationContext(), submitUserResult.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "由于服务器原因，注册失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<SubmitUserResult> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "由于网络原因，注册失败 " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
