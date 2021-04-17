package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/emailService")
public class EmailApplication {
	
	@GetMapping("/send")
	public String getEmail() {
		return "Please Contact Us at abc@gmail.com";
	}

	public static void main(String[] args) {
		SpringApplication.run(EmailApplication.class, args);
	}

}
