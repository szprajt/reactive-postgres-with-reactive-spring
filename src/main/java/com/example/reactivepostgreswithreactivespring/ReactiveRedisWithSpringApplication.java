package com.example.reactivepostgreswithreactivespring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableR2dbcRepositories
@EnableWebFlux
public class ReactiveRedisWithSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveRedisWithSpringApplication.class, args);
	}

}
