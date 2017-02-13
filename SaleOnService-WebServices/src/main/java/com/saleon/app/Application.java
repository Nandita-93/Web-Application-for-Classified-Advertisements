package com.saleon.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.saleon.controller.LoginController;

@SpringBootApplication
@Configuration
@ComponentScan("com.saleon")
@EnableJpaRepositories("com.saleon.dao.repositories")
@EnableAutoConfiguration
@EntityScan("com.saleon.model")
@EnableCaching
@EnableScheduling
public class Application {
	
	public static void main(String[] args)
	{
		SpringApplication springApplication = new SpringApplication(Application.class);
		springApplication.run(Application.class,args);
		
	}

}
