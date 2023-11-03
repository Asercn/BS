package com.as200.bsbd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@MapperScan("com.as200.bsbd.*.mapper")
public class BsBdApplication {
    public static void main(String[] args) {
        SpringApplication.run(BsBdApplication.class, args);
    }
}
