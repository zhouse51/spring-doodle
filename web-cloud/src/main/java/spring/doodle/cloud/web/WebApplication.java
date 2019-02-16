package spring.doodle.cloud.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import brave.sampler.Sampler;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableHystrixDashboard
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
	
	/*
	Set configuration
	1. Command line arguments.
	2. Java System properties (System.getProperties()).
	3. OS environment variables.
	4. JNDI attributes from java:comp/env
	5. A RandomValuePropertySource that only has properties in random.*.
	6. Application properties outside of your packaged jar (application.properties including YAML and profile variants).
	7. Application properties packaged inside your jar (application.properties including YAML and profile variants).
	8. @PropertySource annotations on your @Configuration classes.
	9. Default properties (specified using SpringApplication.setDefaultProperties).
	*/
	@Configuration
	static class WebConfig {
		@Bean
		public Sampler defaultSampler() {
			return Sampler.ALWAYS_SAMPLE;
		}
		
		// use ribbon for load balance
		// check NameService for the sample using feign client
		@Bean
		@LoadBalanced
		public RestTemplate restTemplate() {
			return new RestTemplateBuilder().build();
		}
	}
}

