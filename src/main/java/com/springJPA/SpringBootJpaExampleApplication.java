package com.springJPA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
public class SpringBootJpaExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaExampleApplication.class, args);
	}

}
