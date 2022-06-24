package com.napicu.napicuwebapi.NapicuPocasi;

import javax.validation.constraints.NotNull;

public class NapicuPocasiResponseModel {
    @NotNull
    public String name;
    @NotNull
    public String country;
    @NotNull
    public int pressure;
    @NotNull
    public int temp;
    @NotNull
    public int temp_min;
    @NotNull
    public int temp_max;
    @NotNull
    public int feels_like;
    @NotNull
    public int humidity;
    @NotNull
    public int clouds;
    @NotNull
    public int wind_speed;
    @NotNull
    public String icon;
    @NotNull
    public String description;

}
