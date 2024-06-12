package com.example.spring_web;

import org.springframework.boot.SpringApplication;

public class TestSpringWebApplication {

	public static void main(String[] args) {
		SpringApplication.from(SpringWebApplication::main).with(TestContainersConfiguration.class).run(args);
	}

}
