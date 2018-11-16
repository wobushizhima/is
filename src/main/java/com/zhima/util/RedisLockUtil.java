package com.zhima.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.UUID;

/**
 * Created by superz on 2018/11/14.
 */
public class RedisLockUtil {
    @Autowired
    private RedisTemplate redisTemplate;

    public String acquireLockWithTimeout(){
        redisTemplate.execute((RedisCallback)connection -> {
            connection.setNX()
        });
    }
}
