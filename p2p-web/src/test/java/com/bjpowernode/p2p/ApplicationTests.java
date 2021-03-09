package com.bjpowernode.p2p;

import com.bjpowernode.p2p.common.Constants;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class ApplicationTests {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    public void test01(){
        stringRedisTemplate.opsForValue().set(Constants.KEY_REDIS_SMS+"phone","453201");
    }

}
