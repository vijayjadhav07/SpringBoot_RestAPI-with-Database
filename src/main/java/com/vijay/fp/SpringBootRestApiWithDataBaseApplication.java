package com.vijay.fp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@SpringBootApplication
public class SpringBootRestApiWithDataBaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestApiWithDataBaseApplication.class, args);
		System.err.println("Hello");
	}

	@Bean
	public CommonsMultipartResolver commonsMultipartResolver() {
		return new CommonsMultipartResolver();

	}

}
