package com.example.communicationapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.example.communicationapp.R;
import com.example.communicationapp.database.MyDatabaseHelper;
import com.example.communicationapp.service.GetPositionService;
import com.example.communicationapp.util.LoginUtil;
import com.example.communicationapp.util.PermissionUtil;
import com.example.communicationapp.util.StringUtils;

public class MainActivity extends AppCompatActivity {

    private final static int REQUEST_FOREGROUND_POSITION_CODE = 1;
    private final static int REQUEST_BACKGROUND_POSITION_CODE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, GetPositionService.class);
        startService(intent);


        // 判断展示页面
        Fragment fragment = null;
//        String username = LoginUtil.getUserName(this);
//        if(!StringUtils.isEmpty(username)){
//            Bundle bundle = new Bundle();
//            bundle.putString(getResources().getString(R.string.username_key), username);
//            fragment = UserDetailFragment.newInstance(bundle);
//        }
//        else{
//            fragment = RegisterFragment.newInstance(null);
//        }
        fragment = LocationFragment.newInstance(null);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(fragment != null && !fragment.isAdded()){
            fragmentTransaction.add(R.id.rl_fragment_container, fragment);
            fragmentTransaction.commit();
        }

        // 获取定位权限
        PermissionUtil.requestForegroundPositionPermissions(this, REQUEST_FOREGROUND_POSITION_CODE);

//        PermissionUtil.requestBackgroundPositionPermissions(this, REQUEST_BACKGROUND_POSITION_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case REQUEST_FOREGROUND_POSITION_CODE:
                if(grantResults.length > 0){
                    for(int grantResult : grantResults){
                        if(grantResult != PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this, "请打开定位权限", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }

                    // 申请后台定位权限
                    PermissionUtil.requestBackgroundPositionPermissions(this, REQUEST_BACKGROUND_POSITION_CODE);
                }
                else{
                    Toast.makeText(this, "请打开定位权限", Toast.LENGTH_LONG).show();
                }
            case REQUEST_BACKGROUND_POSITION_CODE:
                if(grantResults.length > 0){
                    for(int grantResult : grantResults){
                        if(grantResult != PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this, "请打开后台定位权限", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                }
                else{
                    Toast.makeText(this, "请打开后台定位权限", Toast.LENGTH_LONG).show();
                }

        }

    }
}
