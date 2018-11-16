package com.zhima.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class DistributedLock {

    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 尝试获取分布式锁
     * @param lockKey 锁
     * @param requestId 请求标识
     * @param expireTime 超期时间
     * @return 是否获取成功
     */
    public  boolean tryGetDistributedLock(String lockKey, String requestId, int expireTime) {

//        String result = redisTemplate.execute((RedisCallback)conn->{
//        });

//        if (LOCK_SUCCESS.equals(result)) {
//            return true;
//        }
        return false;

    }
}