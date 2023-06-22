package com.shopping.cart.reactive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class VaccineController {

	@Autowired
	VaccineService vaccineService;

	@GetMapping("/vaccines")
	public Mono<String> getVaccines(Model model) {
		model.addAttribute("vaccines", vaccineService.getVaccines());
		return Mono.just("vaccine");
	}

}
