package com.w2m.superheroes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class SuperheroesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperheroesApplication.class, args);
	}

}
