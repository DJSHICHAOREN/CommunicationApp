package com.example.communicationapp.util;

public class StringUtils {

    public static boolean isEmpty(String str){
        if(str != null && str != ""){
            return false;
        }
        return true;
    }
}
