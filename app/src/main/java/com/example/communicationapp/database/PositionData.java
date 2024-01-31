package com.example.communicationapp.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.amap.api.location.AMapLocation;

@Entity(tableName = "position_data")
public class PositionData {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private double latitude = 0;

    private double longitude = 0;

    private long timestamp = 0;

    private String address = "";

    private String country = "";
    private String province = "";
    private String city = "";
    private String district= "";
    private String street= "";

    private String errorInfo = "";
    private int errorCode = 0;

    public PositionData() {

    }

    public PositionData(AMapLocation aMapLocation) {
        this.latitude = aMapLocation.getLatitude();
        this.longitude = aMapLocation.getLongitude();
        this.timestamp = System.currentTimeMillis();
    }

    public PositionData(double latitude, double longitude, long timestamp, String address
            , String country, String province, String city, String district, String street, String errorInfo, int errorCode) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
        this.address = address;
        this.country = country;
        this.province = province;
        this.city = city;
        this.district = district;
        this.street = street;
        this.errorInfo = errorInfo;
        this.errorCode = errorCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
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
}
