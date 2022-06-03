package com.napicu.napicuwebapi.NapicuPocasi;

import com.napicu.napicuwebapi.Response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class NapicuPocasiController {

    @Autowired
    private NapicuPocasiService pocasiService;

    @GetMapping("/pocasi")
    public Map<String, String> get(){
        return System.getenv();
    }

}
