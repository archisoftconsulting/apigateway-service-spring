package com.archisoft.b2b;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class GreetingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreetingServiceApplication.class, args);
	}
	
	@RequestMapping("/")
	public String HelloWorld(){
		return "Hello World";
	}
}
