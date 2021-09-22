package com.example.communicationapp.entity;

public class SubmitUserResult {
    // 1:成功, 2:不成功
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }
}
