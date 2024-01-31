package com.example.communicationapp.util;

import android.content.Context;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

public class LocationUtil {

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;

    public static LocationUtil mLocationUtil;

    public LocationUtil(Context context){
        //初始化定位
        try {
            AMapLocationClient.updatePrivacyShow(context, true, true);
            AMapLocationClient.updatePrivacyAgree(context, true);
            mLocationClient = new AMapLocationClient(context);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //声明AMapLocationClientOption对象
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
//        long duration = 1000 * 60 * 15;
        long duration = 1000 * 60;
        mLocationOption.setInterval(duration);

        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);

        //设置定位模式为AMapLocationMode.Device_Sensors，仅设备模式。
//        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Device_Sensors);

        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
    }

    public void setLocationListener(AMapLocationListener locationListener){
        mLocationClient.setLocationListener(locationListener);
    }

    public void startLocation(){
        //启动定位
        mLocationClient.startLocation();

    }

    public void stopLocation(){
        //启动定位
        mLocationClient.stopLocation();

    }

    public static LocationUtil getInstance() {
        if (mLocationUtil == null) {
            mLocationUtil = new LocationUtil(ContextUtil.getContext());
        }
        return mLocationUtil;
    }
}
