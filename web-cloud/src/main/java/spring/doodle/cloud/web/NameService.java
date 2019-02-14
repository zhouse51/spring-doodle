package spring.doodle.cloud.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@EnableFeignClients
public class NameService {
	@Autowired
	private NameFeignClient nameFeignClient;
	
	public String getName() {
		return nameFeignClient.getName();
	}
	
	@FeignClient("name")
	static interface NameFeignClient {
		@RequestMapping("/")
		public String getName();
	}
}
