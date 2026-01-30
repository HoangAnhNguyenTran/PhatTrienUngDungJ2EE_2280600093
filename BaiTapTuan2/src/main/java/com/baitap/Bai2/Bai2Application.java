package com.baitap.Bai2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Bai2Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Bai2Application.class, args);
		System.out.println("Bai2 Application started successfully!");
		System.out.println("Server is running on http://localhost:8080");
	}

}
