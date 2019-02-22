package spring.doodle.cloud.name;

import org.springframework.stereotype.Component;

import spring.doodle.cloud.name.ProfileService.ProfileFeignClient;

//@Component
public class ProfileFeignHystricClient implements ProfileFeignClient {

	@Override
	public String getProfile(String name) {
		return "Profile service not available.";
	}

}
