package com.example.communicationapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.communicationapp.R;
import com.example.communicationapp.entity.SubmitUserResult;
import com.example.communicationapp.entity.User;
import com.example.communicationapp.http.UserService;
import com.example.communicationapp.service.GetPositionService;
import com.example.communicationapp.util.HttpServiceCreator;
import com.example.communicationapp.util.PermissionUtil;
import com.example.communicationapp.util.StringUtils;

public class MainActivity extends AppCompatActivity {

    private final static int OPEN_SET_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, GetPositionService.class);
        startService(intent);

        // 获取用户信息
        String username = "";
        Fragment fragment = null;
        SharedPreferences sharedPreferences = getSharedPreferences(getResources().getString(R.string.login_key), Context.MODE_PRIVATE);
        if(sharedPreferences != null){
             username = sharedPreferences.getString(getResources().getString(R.string.username_key), "");
        }

        // 判断展示页面
        if(!StringUtils.isEmpty(username)){
            Bundle bundle = new Bundle();
            bundle.putString(getResources().getString(R.string.username_key), username);
            fragment = UserDetailFragment.newInstance(bundle);
        }
        else{
            fragment = RegisterFragment.newInstance(null);
        }

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(fragment != null && !fragment.isAdded()){
            fragmentTransaction.add(R.id.rl_fragment_container, fragment);
            fragmentTransaction.commit();
        }

        // 获取定位权限
        PermissionUtil.requestPositionPermissions(this, OPEN_SET_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case OPEN_SET_REQUEST_CODE:
                if(grantResults.length > 0){
                    for(int grantResult : grantResults){
                        if(grantResult != PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this, "请打开定位权限", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                }
                else{
                    Toast.makeText(this, "请打开定位权限", Toast.LENGTH_LONG).show();
                }

        }

    }
}
