package com.napicu.napicuwebapi.service;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Objects;

@Service
public class RateLimit {

    @Value("${api.limits}")
    private String limits;
    private final Bucket bucket;
    private final String defaultRateLimits = "40";


    RateLimit() {

        if (Objects.equals(this.limits, null)) {
            this.limits = defaultRateLimits;
            new NapicuPrint().printInfo("Limits are not set, the limits have been set at " + defaultRateLimits);
        }

        Bandwidth limit = Bandwidth.classic(Integer.parseInt(this.limits), Refill.greedy(1, Duration.ofMinutes(1)));
        this.bucket = Bucket4j.builder()
                .addLimit(limit)
                .build();
    }

    public Bucket getServiceBucket() {
        return this.bucket;
    }
}
