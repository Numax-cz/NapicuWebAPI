package com.napicu.napicuwebapi.service;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class RateLimit {



    public Bucket getServiceBucket(){

        return  Bucket.builder().addLimit(Bandwidth.classic(5, Refill.intervally(2, Duration.ofMinutes(1)))).build();
    }


}
