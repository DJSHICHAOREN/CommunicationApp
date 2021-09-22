package com.example.communicationapp.entity;

import com.amap.api.location.AMapLocation;
import com.example.communicationapp.util.TimeUtil;

public class Position {
    public double latitude;

    public double longitude;

    private float accuracy;

    private double altitude;

    private float speed;

    private float bearing;

    private String buildingId;

    private String floor;

    private String address;

    private String country;
    private String province;
    private String city;
    private String district;
    private String street;

    private String streetNum;
    private String cityCode;
    private String adCode;
    private String poiName;
    private String aoiName;

    private int gpsAccuracyStatus;
    private int locationType;
    private String locationDetail;

    private String errorInfo;
    private int errorCode;

    private long time;

    public Position(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Position(AMapLocation aMapLocation){
        if (aMapLocation != null){
            this.latitude = aMapLocation.getLatitude();
            this.longitude = aMapLocation.getLongitude();
            this.accuracy = aMapLocation.getAccuracy();
            this.altitude = aMapLocation.getAltitude();
            this.speed = aMapLocation.getSpeed();
            this.bearing = aMapLocation.getBearing();
            this.buildingId = aMapLocation.getBuildingId();
            this.floor = aMapLocation.getFloor();
            this.address = aMapLocation.getAddress();
            this.country = aMapLocation.getCountry();
            this.province = aMapLocation.getProvince();
            this.accuracy = aMapLocation.getAccuracy();
            this.city = aMapLocation.getCity();
            this.district = aMapLocation.getDistrict();
            this.street = aMapLocation.getStreet();
            this.streetNum = aMapLocation.getStreetNum();
            this.cityCode = aMapLocation.getCityCode();
            this.adCode = aMapLocation.getAdCode();
            this.poiName = aMapLocation.getPoiName();
            this.aoiName = aMapLocation.getAoiName();
            this.gpsAccuracyStatus = aMapLocation.getGpsAccuracyStatus();
            this.locationType = aMapLocation.getLocationType();
            this.locationDetail = aMapLocation.getLocationDetail();
            this.errorInfo = aMapLocation.getErrorInfo();
            this.errorCode = aMapLocation.getErrorCode();

            this.time = TimeUtil.getCurrentTimeStamp();
        }
    }
}
