package com.example.communicationapp.entity;

public class SubmitUserParam {

    private Device device;
    private User user;

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SubmitUserParam(){

    }

    public SubmitUserParam(User user, Device device) {
        this.user = user;
        this.device = device;
    }
}
