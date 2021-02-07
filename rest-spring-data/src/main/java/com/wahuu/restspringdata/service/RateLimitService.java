package com.wahuu.restspringdata.service;

import com.wahuu.restspringdata.model.RedisCounter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RateLimitService {

    private Logger logger = LoggerFactory.getLogger(RateLimitService.class);

    @Autowired
    private RedisTemplate<String, RedisCounter> redisTemplate;

    public void noteApiCall(String serviceName) {
        logger.info("Noting api call for service {}", serviceName);
        RedisCounter currentRateLimit = getCurrentRateLimit(serviceName);
        logger.info("Current api call rate for service {} is {}", serviceName, currentRateLimit);
        redisTemplate.opsForValue().set(serviceName, currentRateLimit.increment());
    }

    public RedisCounter getCurrentRateLimit(String serviceName) {
        RedisCounter redisCounter = redisTemplate.opsForValue().get(serviceName);
        if (redisCounter != null) {
            return redisCounter;
        } else {
            return new RedisCounter(0L);
        }
    }

}
