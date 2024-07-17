package com.project.coffee;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@ComponentScan(basePackages = { "com.project.controller", "com.project.config"})
public class ILaProjectCoffeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ILaProjectCoffeeApplication.class, args);
	}

}
