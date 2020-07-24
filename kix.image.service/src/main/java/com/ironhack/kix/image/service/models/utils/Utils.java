package com.ironhack.kix.image.service.models.utils;

import com.google.common.hash.Hashing;

public class Utils {
    public static String stringToHash256(String st){
       return Hashing.sha256().hashBytes(st.getBytes()).toString();
    }

    public static String bytesToHash256(byte[] bt){
        return Hashing.sha256().hashBytes(bt).toString();
    }
}
