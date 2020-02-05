package com.tek.trp.CustomerService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.tek.trp.controller", "com.tek.trp.service","com.tek.trp.model",
		"com.tek.trp.repository","com.tek.trp.service.*" })
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

}
