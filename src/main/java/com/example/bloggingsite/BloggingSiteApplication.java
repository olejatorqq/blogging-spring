package com.example.bloggingsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class BloggingSiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloggingSiteApplication.class, args);
	}

}
