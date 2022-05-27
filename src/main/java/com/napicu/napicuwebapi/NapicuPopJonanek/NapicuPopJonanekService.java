package com.napicu.napicuwebapi.NapicuPopJonanek;

import com.napicu.napicuwebapi.Database.NapicuPopJonanekDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NapicuPopJonanekService {
    @Autowired
    private NapicuPopJonanekRepository repo;

    protected final int minCount = 0;

    protected final int maxCount = 2000;

    public int updateAndGetCounter(int count){
        boolean exists = repo.existsById(1);

        NapicuPopJonanekDatabase database;

        if(!exists){
            database  = creatNewDatabase();
        } else database = repo.findById(1).get();

        if(count > minCount && count < maxCount){
            database.setCounter(count + database.getCounter());
            database = repo.save(database);
        }

        return database.getCounter();
    }

    public NapicuPopJonanekDatabase creatNewDatabase(){
        NapicuPopJonanekDatabase db = new NapicuPopJonanekDatabase();
        db.setId(1);
        db.setCounter(0);
        return db;
    }
}

