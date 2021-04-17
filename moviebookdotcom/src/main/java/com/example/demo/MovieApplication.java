package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@SpringBootApplication
@RestController
@EnableHystrix
@EnableHystrixDashboard
public class MovieApplication {

	@Autowired
	private RestTemplate template;
	
	@HystrixCommand(groupKey = "moviebooknow",commandKey = "moviebooknow",fallbackMethod = "movieBookFallback")
	@GetMapping("moviebooknow")
	public String moviebookShow() {
		String emailServiceResponse = template.getForObject( "http://localhost:8181/emailService/send", String.class);
		String paymentServiceResponse = template.getForObject( "http://localhost:8282/paymentService/pay", String.class);
		return emailServiceResponse +"\n"+paymentServiceResponse;
	}
	@GetMapping("moviebooknowwithouthystrix")
	public String moviebookShowWithoutHystrix() {
		String emailServiceResponse = template.getForObject( "http://localhost:8181/emailService/send", String.class);
		String paymentServiceResponse = template.getForObject( "http://localhost:8282/paymentService/pay", String.class);
		return emailServiceResponse +"\n"+paymentServiceResponse;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MovieApplication.class, args);
	}

	public String movieBookFallback() {
		return "Your request is not suceesfull,Please try again after sometime";
	}
	
	@Bean
	public RestTemplate template() {
		return new RestTemplate(); 
	}
}
