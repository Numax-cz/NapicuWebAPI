package com.napicu.napicuwebapi.NapicuPopJonanek;

import com.napicu.napicuwebapi.Database.NapicuPopJonanekDatabase;
import com.napicu.napicuwebapi.Response.Response;
import com.napicu.napicuwebapi.service.NapicuPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class NapicuPopJonanekService {
    @Autowired
    private NapicuPopJonanekRepository repo;

    protected final int minCount = 0;

    protected final int maxCount = 2000;

    public Response updateAndGetCounter(int count){
        NapicuPopJonanekDatabase database;

        try {
            boolean exists = repo.existsById(1);

            if(!exists){
                database  = creatNewDatabase();
            } else database = repo.findById(1).get();

            if(count > minCount && count < maxCount){
                database.setCounter(count + database.getCounter());
                database = repo.save(database);
            }
        }catch (Exception error){
            new NapicuPrint().printError("NapicuPopJonanekService", error.toString());
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
        }

        return new Response(HttpStatus.OK.value(), database.getCounter());
    }

    public NapicuPopJonanekDatabase creatNewDatabase(){
        NapicuPopJonanekDatabase db = new NapicuPopJonanekDatabase();
        db.setId(1);
        db.setCounter(0);
        return db;
    }
}

