package spring.doodle.cloud.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Service
//@EnableFeignClients
public class NameService {

	private static final String URL = "http://NAME";
	
	private RestTemplate restTemplate;
	
	public NameService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
//	@Autowired
//	private NameFeignClient nameFeignClient;
	
	public String getName() {
//		return nameFeignClient.getName();
		return restTemplate.getForObject(URL, String.class);
	}
	
	// feign client.
	// enable feign hystrix configuration 
//	@FeignClient(value = "name",fallback = NameFeignHystricClient.class)
//	static interface NameFeignClient {
//		@RequestMapping("/")
//		public String getName();
//	}
}
