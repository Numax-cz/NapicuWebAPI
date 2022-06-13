package com.napicu.napicuwebapi.NapicuPopJonanek;

import com.napicu.napicuwebapi.Database.NapicuPopJonanekDatabase;
import com.napicu.napicuwebapi.exception.NapicuExceptions;
import com.napicu.napicuwebapi.exception.RequestException;
import com.napicu.napicuwebapi.exception.RequestExceptionSchema;
import com.napicu.napicuwebapi.service.NapicuPrint;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NapicuPopJonanekService {
    @Autowired
    private NapicuPopJonanekRepository repo;

    protected final int minCount = 0;

    protected final int maxCount = 2000;


    public NapicuPopJonanekResponseModel updateAndGetCounter(int count) {
        NapicuPopJonanekDatabase database;
        try {
            boolean exists = repo.existsById(1);
            if (!exists) {
                database = creatNewDatabase();
            } else database = repo.findById(1).get();

            if (count > minCount && count < maxCount) {
                database.setCounter(count + database.getCounter());
                database = repo.save(database);
        }
        } catch (Exception error) {
            new NapicuPrint().printError("NapicuPopJonanekService", error.toString());
            throw new RequestException(HttpStatus.INTERNAL_SERVER_ERROR, NapicuExceptions.NAPICU_SERVER_DATABASE_ERROR);
        }
        NapicuPopJonanekResponseModel data = new NapicuPopJonanekResponseModel();
        data.counter = database.getCounter();
        return data;
    }

    public NapicuPopJonanekDatabase creatNewDatabase() {
        NapicuPopJonanekDatabase db = new NapicuPopJonanekDatabase();
        db.setId(1);
        db.setCounter(0);
        return db;
    }
}

