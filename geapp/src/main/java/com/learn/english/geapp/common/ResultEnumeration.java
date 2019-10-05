package com.learn.english.geapp.common;

public enum ResultEnumeration {
    OK,
    KO;
    
    public String value() {
        return name();
    }

    public static ResultEnumeration fromValue(String v) {
        return valueOf(v);
    }

}
