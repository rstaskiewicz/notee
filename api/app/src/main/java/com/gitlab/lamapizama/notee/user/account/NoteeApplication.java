package com.gitlab.lamapizama.notee.user.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(NoteeProperties.class)
@SpringBootApplication
public class NoteeApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoteeApplication.class, args);
    }
}
