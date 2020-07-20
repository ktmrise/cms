package com.ktm;


import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ktm.mapper")
@Slf4j
public class CmsApplication {
    public static void main(String[] args) {

        SpringApplication.run(CmsApplication.class, args);
        log.info("http://localhost:8080");
    }
}
