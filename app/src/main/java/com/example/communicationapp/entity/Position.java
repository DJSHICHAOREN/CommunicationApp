package com.example.communicationapp.entity;

import com.amap.api.location.AMapLocation;
import com.example.communicationapp.database.PositionData;
import com.example.communicationapp.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;

public class Position {
    private double latitude;

    private double longitude;

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

    public Position(double latitude, double longitude, long time) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = time;
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

    public static Position parse(PositionData positionData) {
        return new Position(positionData.getLatitude(), positionData.getLongitude(), positionData.getTimestamp());
    }

    public static List<Position> parse(List<PositionData> positionDataList) {
        List<Position> positionList = new ArrayList<>();
        for (PositionData positionData : positionDataList) {
            positionList.add(parse(positionData));
        }
        return positionList;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getBearing() {
        return bearing;
    }

    public void setBearing(float bearing) {
        this.bearing = bearing;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNum() {
        return streetNum;
    }

    public void setStreetNum(String streetNum) {
        this.streetNum = streetNum;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getAdCode() {
        return adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }

    public String getPoiName() {
        return poiName;
    }

    public void setPoiName(String poiName) {
        this.poiName = poiName;
    }

    public String getAoiName() {
        return aoiName;
    }

    public void setAoiName(String aoiName) {
        this.aoiName = aoiName;
    }

    public int getGpsAccuracyStatus() {
        return gpsAccuracyStatus;
    }

    public void setGpsAccuracyStatus(int gpsAccuracyStatus) {
        this.gpsAccuracyStatus = gpsAccuracyStatus;
    }

    public int getLocationType() {
        return locationType;
    }

    public void setLocationType(int locationType) {
        this.locationType = locationType;
    }

    public String getLocationDetail() {
        return locationDetail;
    }

    public void setLocationDetail(String locationDetail) {
        this.locationDetail = locationDetail;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
