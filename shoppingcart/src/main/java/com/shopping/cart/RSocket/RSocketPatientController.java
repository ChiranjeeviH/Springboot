package com.shopping.cart.RSocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import reactor.core.publisher.Mono;

@Controller
public class RSocketPatientController {
	
	Logger LOGGER = LoggerFactory.getLogger(RSocketPatientController.class);


	@MessageMapping("get-patient-data")
	public Mono<ClinicalData> requestResponse(@RequestBody Patient patient){
		LOGGER.info("Sending the rSocket request for patient "+patient);
		return  Mono.just(new ClinicalData(90, 80/120));
		
	}
	
	@MessageMapping("patient-checkout")
	public Mono<Void> fireAndForget(Patient patient){
		LOGGER.info("Patient Checkedout"+patient);
		LOGGER.info("Billing initiated");
		return Mono.empty().then();
	}
}
