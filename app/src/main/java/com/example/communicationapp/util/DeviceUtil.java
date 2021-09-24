package com.example.communicationapp.util;

import android.content.Context;
import android.provider.Settings;

import com.example.communicationapp.entity.Device;

public class DeviceUtil {

    public static Device getDevice(Context context){
        String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return new Device(androidId);
    }
}
