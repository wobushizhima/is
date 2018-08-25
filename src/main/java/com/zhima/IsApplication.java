package com.zhima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class IsApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsApplication.class, args);
	}
}
