package com.shopping.cart.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {
	
	Logger LOGGER = LoggerFactory.getLogger(MessageSender.class);
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Value("${springjms.myQueue}")
	private String queue;
	
	public void send(String message) {
		//below line of code changes the default messaging mechanism Queue to Topic
		//jmsTemplate.setPubSubDomain(true);
		LOGGER.info("Sent message "+message);
		jmsTemplate.convertAndSend(queue,message);
	}
	

}
