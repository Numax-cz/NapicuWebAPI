package com.napicu.napicuwebapi.NapicuPopJonanek;

import com.napicu.napicuwebapi.Database.NapicuPopJonanekDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NapicuPopJonanekService {
    @Autowired
    private NapicuPopJonanekRepository repo;

    protected final int minCount = 0;
    protected final int maxCount = 2000;

    public int updateAndGetCounter(int count){
        Optional<NapicuPopJonanekDatabase> d = repo.findById(1);
        NapicuPopJonanekDatabase event = d.get();

        if(count > minCount && count < maxCount){
            event.setCounter(count + event.getCounter());
            event = repo.save(event);
        }

        return event.getCounter();
    }
}

