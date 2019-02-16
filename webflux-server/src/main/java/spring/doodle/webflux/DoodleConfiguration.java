package spring.doodle.webflux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import spring.doodle.webflux.service.DoodleService;

@Configuration
@ComponentScan(value={"spring.doodle.SpringDoodle.service"})
public class DoodleConfiguration {
	@Bean
	public DoodleService getMDoodleService(){
		return new DoodleService();
	}
}
