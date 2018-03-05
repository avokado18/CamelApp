package ru.cinimex.camel_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages="ru.cinimex.camel_app")
public class Main {
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}
