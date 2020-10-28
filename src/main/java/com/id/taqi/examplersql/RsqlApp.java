package com.id.taqi.examplersql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.id.taqi")
public class RsqlApp {
	public static void main(String[] args) {
		SpringApplication.run(RsqlApp.class, args);
	}
}