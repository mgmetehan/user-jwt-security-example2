package com.mgmetehan.userjwtsecurityexample2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class UserJwtSecurityExample2Application {


    public static void main(String[] args) {
        SpringApplication.run(UserJwtSecurityExample2Application.class, args);
    }
}
