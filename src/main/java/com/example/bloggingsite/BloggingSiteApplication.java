package com.example.bloggingsite;

import com.example.bloggingsite.controllers.GreetingsController;
import com.example.bloggingsite.repos.MessageRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;


@SpringBootApplication
public class BloggingSiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloggingSiteApplication.class, args);
	}

}
