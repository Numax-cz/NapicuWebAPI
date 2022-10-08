package com.napicu.napicuwebapi.NapicuBiosWaitList;

import com.napicu.napicuwebapi.Database.NapicuBiosWaitListDatabase;
import com.napicu.napicuwebapi.exception.NapicuExceptions;
import com.napicu.napicuwebapi.exception.RequestException;
import com.napicu.napicuwebapi.service.NapicuPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

//@Service
public class NapicuBiosWaitListService {


    @Autowired
    private NapicuBiosWaitListRepository repo;

    public void insertEmailToDatabase(String email) {
        NapicuBiosWaitListDatabase database = new NapicuBiosWaitListDatabase();
        try{
            database.setEmail(email);
            this.repo.save(database);
        }catch (Exception error){
            new NapicuPrint().printError("NapicuBiosWaitListService", error.toString());
            throw new RequestException(HttpStatus.INTERNAL_SERVER_ERROR, NapicuExceptions.NAPICU_SERVER_DATABASE_ERROR);
        }

    }

}
