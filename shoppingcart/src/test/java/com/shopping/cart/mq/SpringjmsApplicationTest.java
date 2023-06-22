package com.shopping.cart.mq;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringjmsApplicationTest {

	@Autowired
	MessageSender sender;

	@Test
	public void testSendAndReceive() {
		sender.send("Hello Spring JMS !!!");
	}

}
