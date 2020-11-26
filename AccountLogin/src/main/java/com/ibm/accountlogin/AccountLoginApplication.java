package com.ibm.accountlogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AccountLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountLoginApplication.class, args);
	}

}
