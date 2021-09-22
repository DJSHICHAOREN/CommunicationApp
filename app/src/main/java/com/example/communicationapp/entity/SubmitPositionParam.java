package com.example.communicationapp.entity;

import com.example.communicationapp.entity.Device;
import com.example.communicationapp.entity.Position;

public class SubmitPositionParam {
    private Device device;
    private Position position;
    private User user;

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
