package com.shopping.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.jms.annotation.EnableJms;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableCaching
@EnableJms
@OpenAPIDefinition(info = @Info( title = "Item API ", version = "1.0" , description = "Shopping cart"))
public class ShoppingcartApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ShoppingcartApplication.class, args);
		
	}
	

}
