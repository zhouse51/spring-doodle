package spring.doodle.cloud.web;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class GreetingService {
	private static final String URL = "http://GREETING";
	
	private RestTemplate restTemplate;

	public GreetingService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@HystrixCommand(fallbackMethod="greetingError")
	public String getGreeting() {
		return restTemplate.getForObject(URL, String.class);
	}

	@HystrixCommand(fallbackMethod="greetingError")
	public String getGreeting(String locale) {
		return restTemplate.getForObject(new StringBuilder().append(URL).append("/").append(locale).toString(), String.class);
	}
	
	public String greetingError(String locale) {
		return "Greeting " + locale + " has error.";
	}
}
