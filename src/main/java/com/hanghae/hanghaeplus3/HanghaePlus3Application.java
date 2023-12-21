package com.hanghae.hanghaeplus3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HanghaePlus3Application {

    public static void main(String[] args) {
        SpringApplication.run(HanghaePlus3Application.class, args);
    }

}
