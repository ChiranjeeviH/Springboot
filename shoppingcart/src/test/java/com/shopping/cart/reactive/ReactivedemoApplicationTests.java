package com.shopping.cart.reactive;

import java.time.Duration;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootTest
public class ReactivedemoApplicationTests {
	
	@Autowired
	VaccineProvider provider;
	
	@Test
	void testVaccineProvider() {
		provider.provideVaccines().subscribe(new VaccineConsumer());
	}

	@Test
	void testMono() {

		Mono<String> mono = Mono.just("Chiru Notebook");
		mono.log().map(data -> data.toUpperCase()).subscribe(System.out::println);
	}

	@Test
	void testFlux() throws InterruptedException {

		/*
		 * Flux<String> flux =
		 * Flux.just("Chiru Notebook","iPhone","AirPods").delayElements(Duration.
		 * ofSeconds(2));
		 * flux.log().map(data->data.toUpperCase()).subscribe(System.out::println);
		 */

		Flux.fromIterable(Arrays.asList("Chiru Notebook", "iPhone", "AirPods")).delayElements(Duration.ofSeconds(2))
				.log().map(data -> data.toUpperCase()).subscribe(new Subscriber<Object>() {

					private long count = 0;
					private Subscription subscription;

					@Override
					public void onSubscribe(Subscription subscription) {
						this.subscription = subscription;
						subscription.request(2);
					}

					@Override
					public void onNext(Object items) {
						count++;
						if (count >= 2) {
							count = 0;
							subscription.request(2);
						}
						System.out.println(items);
					}

					@Override
					public void onError(Throwable t) {
						t.printStackTrace();
					}

					@Override
					public void onComplete() {
						System.out.println("completely done");
					}
				});
		Thread.sleep(10000);
	}

}
