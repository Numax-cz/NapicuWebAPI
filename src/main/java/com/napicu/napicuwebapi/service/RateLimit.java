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
    private final Bucket bucket;

    RateLimit(){
        Bandwidth limit = Bandwidth.classic(40, Refill.greedy(1, Duration.ofMinutes(1)));
        this.bucket = Bucket4j.builder()
                .addLimit(limit)
                .build();
    }

    public Bucket getServiceBucket() {
        return this.bucket;
    }
}
