package spring.doodle.cloud.web;

import org.springframework.stereotype.Component;

import spring.doodle.cloud.web.NameService.NameFeignClient;

@Component
public class NameFeignHystricClient implements NameFeignClient {

	@Override
	public String getName() {
		return "Name Out Of Service";
	}

}
