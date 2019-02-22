package spring.doodle.cloud.name;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
//import org.springframework.cloud.netflix.hystrix.EnableHystrix;
//import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import brave.sampler.Sampler;

@SpringBootApplication
@EnableConfigurationProperties(NameProperties.class)
@EnableDiscoveryClient
//@EnableHystrix
//@EnableHystrixDashboard
public class NameApplication {

	public static void main(String[] args) {
		SpringApplication.run(NameApplication.class, args);
	}

	@Configuration
	static class WebConfig {
		// use Zipkin for trace
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

