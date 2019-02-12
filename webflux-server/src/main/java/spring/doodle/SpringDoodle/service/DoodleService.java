package spring.doodle.SpringDoodle.service;

import java.time.Duration;
import java.util.Date;
import java.util.Random;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.doodle.SpringDoodle.data.GirlProperties;

public class DoodleService {
	public String processData(String data) {
		GirlProperties g = new GirlProperties();
		return "processed data in service: " + data + " " + g.getName();
	}

	public Mono<String> findOne(String id) {
		System.out.println(new Date() + " Start process ID: " + id);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(new Date() + " Finished process ID: " + id);
		
		return Mono.just("Result for ID: " + id);
	}
	
	public void subscriber() {
		String baseUrl = "http://localhost:8080/api";
		WebClient client = WebClient.create(baseUrl);
		
		System.out.println(new Date() + " Start subscriber");
		
        for (int i = 1; i <= 5; i++) {
//        	client
//        		.get()
//	        	.uri("/person/{id}", i)
//	        	.retrieve()
//	        	.bodyToMono(String.class)
//	        	.subscribe(
//	        		value -> processSubscribtionResult(value), 
//					error -> error.printStackTrace()
//	        	);
        	
        	client
	    		.get()
	        	.uri("/person/{id}", i)
	        	.accept(MediaType.APPLICATION_JSON)
	            .exchange()
	            .map(cr -> cr.bodyToMono(String.class))
	            .flatMap(cr -> processSubscribtionResult(cr))
	            .subscribe();
	        
        	
        }
        System.out.println(new Date() + " End subscriber");
        
        System.out.println(new Date() + " Working on something else started.");
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        System.out.println(new Date() + " Working on something else finished.");
        
	}
	
	private void processSubscribtionResult(String x) {
		System.out.println(new Date() + " Returned: " + x);
	}
	private Mono<String> processSubscribtionResult(Mono<String> x) {
		System.out.println(new Date() + " Returned: " + x);
		
		return x.flatMap(v -> {
			System.out.println(new Date() + " Returned: " + v); 
			return Mono.just(v); });
	}
	
	public Flux<Long> getRandom(long seed) {
		Random r = new Random(seed);
		return Flux.interval(Duration.ofSeconds(1))
				.map(x->r.nextLong());
	}
}
