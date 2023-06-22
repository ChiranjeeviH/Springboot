package com.shopping.cart.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListner {
	
	Logger LOGGER = LoggerFactory.getLogger(MessageListner.class);
	
	@JmsListener(destination = "${springjms.myQueue}")
	public void receive(String message) {
		
		LOGGER.info("Message received ==> "+message);
		
	}

}
