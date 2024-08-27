package com.greatnex.semicolon_task.config;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableJpaRepositories
//@ComponentScan(basePackages = "com.greatnex.semicolon_task")
//@EntityScan(basePackages = "com.greatnex.semicolon_task")
@EnableAutoConfiguration
public class DataConfig {
}
