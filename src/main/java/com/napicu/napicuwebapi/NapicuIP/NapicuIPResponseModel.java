package com.napicu.napicuwebapi.NapicuIP;

import javax.validation.constraints.NotNull;

public class NapicuIPResponseModel {
    @NotNull
    public String query;
    @NotNull
    public String country;
    @NotNull
    public String city;
    @NotNull
    public String org;
}
