package com.example.communicationapp.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionUtil {



    public static void requestPositionPermissions(Activity activity, int requestCode){
        //权限数组（申请定位）
        String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS};
        requestPermissions(permissions, activity, requestCode);
    }

    private static void requestPermissions(String[] permissions, Activity activity, int requestCode) {
        if (isLacksPermission(permissions, activity)) {//判断是否拥有权限
            //请求权限，第二参数权限String数据，第三个参数是请求码便于在onRequestPermissionsResult 方法中根据code进行判断
            ActivityCompat.requestPermissions(activity, permissions, requestCode);
        } else {
            //拥有权限执行操作
        }
    }

    //如果返回true表示缺少权限
    public static boolean isLacksPermission(String[] permissions, Context context) {
        for (String permission : permissions) {
            //判断是否缺少权限，true=缺少权限
            if(ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED){
                return true;
            }
        }
        return false;
    }
}
