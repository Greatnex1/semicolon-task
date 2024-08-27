package com.greatnex.semicolon_task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.greatnex.semicolon_task")
@EnableJpaRepositories("com.greatnex.semicolon_task.repository")
//@ComponentScan(basePackages = "com.greatnex.semicolon_task")
public class SemicolonTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(SemicolonTaskApplication.class, args);
	}

}
