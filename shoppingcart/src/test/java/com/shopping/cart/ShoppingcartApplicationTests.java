package com.shopping.cart;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.shopping.cart.model.Item;

@SpringBootTest
class ShoppingcartApplicationTests {
	
	RestTemplate restTemplate = new RestTemplate();
	
	@Value("${userrestapi.services.url}")
	private String baseURL;

	@Test
	void contextLoads() {
		Assertions.assertEquals("test", "test");
	}
	
	@Test
	public void testGetItem() {
		Item item = restTemplate.getForObject(baseURL+"/get/1", Item.class);
		Assertions.assertEquals("Shirt", item.getItemName());
	}

}
