package com.hz.smsgate;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SmsGateApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmsGateApplication.class, args);
	}
}
