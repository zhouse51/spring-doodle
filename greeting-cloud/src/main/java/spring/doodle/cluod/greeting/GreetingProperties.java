package spring.doodle.cluod.greeting;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
public class GreetingProperties {
	private String greeting;
	private Map<String, String> greetings;

	public String getGreeting() {
		return greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}

	public Map<String, String> getGreetings() {
		return greetings;
	}

	public void setGreetings(Map<String, String> greetings) {
		this.greetings = greetings;
	}
}
