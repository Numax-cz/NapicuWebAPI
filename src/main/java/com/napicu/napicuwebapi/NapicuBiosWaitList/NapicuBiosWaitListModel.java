package com.napicu.napicuwebapi.NapicuBiosWaitList;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.media.ExampleObject;

import javax.validation.constraints.NotNull;

public class NapicuBiosWaitListModel {
    @NotNull
    public String email;
}
