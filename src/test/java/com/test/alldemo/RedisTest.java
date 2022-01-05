package com.test.alldemo;

import com.test.alldemo.constants.Cachekey;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
@Slf4j
public class RedisTest {
    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Test
    public void stringTest(){
        String key = Cachekey.TEST_KEY+"string";
        redisTemplate.opsForValue().set(key,"111");

        log.info("redis value is :{}",redisTemplate.opsForValue().get(key));
    }

}
