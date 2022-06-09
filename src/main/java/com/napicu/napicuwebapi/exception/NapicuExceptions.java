package com.napicu.napicuwebapi.exception;

public enum NapicuExceptions {

    //Server
    NAPICU_SERVER_ERROR(10),
    NAPICU_SERVER_DATABASE_ERROR(20),
    NAPICU_POCASI_CITY_NOT_FOUND(1030),

    NAPICU_BAD_IP(2030);




    private int value;
    private NapicuExceptions(int value) {
        this.value = value;
    }
    public int value() {
        return value;
    }
}
