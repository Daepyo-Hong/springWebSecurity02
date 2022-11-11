package com.green.nowon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class SpringWebSecurity02Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebSecurity02Application.class, args);
    }

}
