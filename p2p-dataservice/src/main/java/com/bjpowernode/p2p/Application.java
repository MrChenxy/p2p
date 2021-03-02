package com.bjpowernode.p2p;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
@MapperScan(basePackages = "com.bjpowernode.p2p.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
