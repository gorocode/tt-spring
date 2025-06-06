package com.goro.tabletalk.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = "com.goro.tabletalk")
@EntityScan(basePackages = "com.goro.tabletalk.entity")
@EnableJpaRepositories(basePackages = "com.goro.tabletalk.repository")
@SpringBootApplication
public class TableTalkApplication {
    public static void main(String[] args) {
        SpringApplication.run(TableTalkApplication.class, args);
    }

}
