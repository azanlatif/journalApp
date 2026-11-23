package com.azan.journalApp.service;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTests {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Disabled
    @Test
    void testRedisTemplate() {
        Object value = redisTemplate.opsForValue().get("kry");
        System.out.println(value);
    }
}
