package spring.doodle.cloud.name;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Service
@EnableFeignClients
public class ProfileService {
	private static final String URL = "http://PROFILE";
	
	@Autowired
	private RestTemplate restTemplate;
	
//	@Autowired
//	private ProfileFeignClient profileFeignClient;
	
	public String getProfile(String name) {
//		return profileFeignClient.getProfile(name);
		return restTemplate.getForObject(URL + "/" + name, String.class);
	}

//	@FeignClient(value = "profile", fallback = ProfileFeignHystricClient.class)
	static interface ProfileFeignClient {
		@RequestMapping(value = "/{name}" ,method = RequestMethod.GET)
		public String getProfile(@PathVariable("name") String name);
	}

}
