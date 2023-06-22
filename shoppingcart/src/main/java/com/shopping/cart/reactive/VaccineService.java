package com.shopping.cart.reactive;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class VaccineService {
	
	public Flux<Vaccine> getVaccines(){
		
		return Flux.just(new Vaccine("CoviShiled"),new Vaccine("CoVaccine"),new Vaccine("J&J"));
	}
	

}
