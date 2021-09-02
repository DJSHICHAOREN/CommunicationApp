package com.example.communicationapp.http;

import com.amap.api.location.AMapLocation;

public class Position {
    public double latitude;

    public double longitude;

    public Position(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Position(AMapLocation aMapLocation){
        if (aMapLocation != null){
            this.latitude = aMapLocation.getLatitude();
            this.longitude = aMapLocation.getLongitude();
        }
    }
}
