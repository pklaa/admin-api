package com.dmc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@MapperScan("com.dmc.mapper")
@ComponentScan(basePackages = {"com.dmc.swagger2","com.dmc"})
public class DhamechaAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(DhamechaAdminApplication.class, args);
    }

}
