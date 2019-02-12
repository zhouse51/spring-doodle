package spring.doodle.SpringDoodle.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.doodle.SpringDoodle.service.DoodleService;

@RestController
@RequestMapping(path = "/api")
public class DoodleResource {
	@Autowired
	private DoodleService service;
	
	@GetMapping(path="/process/{data}", produces = "application/json")
	public String process(@PathVariable("data") String data) {
		return service.processData(data + " " );
	}
	
	@GetMapping("/random/{seed}")
	public Flux<Long> getRandom(@PathVariable long seed) {
		return this.service.getRandom(seed);
	}
	
	@GetMapping("/person/{id}")
	public Mono<String> findById(@PathVariable String id) {
		return this.service.findOne(id);
	}
	
	@GetMapping("/subscribe")
	public void subscribe() {
		service.subscriber();
	}
}
