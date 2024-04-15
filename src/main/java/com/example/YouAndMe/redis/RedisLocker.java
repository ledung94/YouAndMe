package com.example.YouAndMe.redis;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

// @Component
// public class RedisLocker {
//     private final RedisTemplate<Object, Object> redisTemplate;

//     @Autowired
//     public RedisLocker(RedisTemplate<Object, Object> redisTemplate) {
//         this.redisTemplate = redisTemplate;
//     }

//     public boolean acquireLock(String lockKey, String lockValue, long timeout) {
//         Boolean success = redisTemplate.opsForValue().setIfAbsent(lockKey, lockValue, timeout,
//                 TimeUnit.MILLISECONDS);
//         return Boolean.TRUE.equals(success);
//     }

//     public void releaseLock(String lockKey) {
//         redisTemplate.delete(lockKey);
//     }
// }
