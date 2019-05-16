package com.enn.commodity.synergistic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
@EnableTransactionManagement 
@SpringBootApplication(scanBasePackages={
		"com.enn.commodity.synergistic", "com.commodity"})
public class EnnApplication {
	
	@Autowired
	RestTemplateBuilder restTemplateBuilder;

	public static void main(String[] args) {
		SpringApplication.run(EnnApplication.class, args);
	}
	
	@Bean
    public RestTemplate restTemplate(){
        return  restTemplateBuilder.build();
    }
}
