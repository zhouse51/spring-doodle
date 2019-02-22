package spring.doodle.cloud.profile;


import java.util.logging.Logger;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RefreshScope
public class ProfileController {
	private static final Logger LOG = Logger.getLogger(ProfileController.class.getName());
	
	private ProfileProperties profileProperties;

	public ProfileController(ProfileProperties profileProperties) {
		this.profileProperties = profileProperties;
	}

	@RequestMapping("/{name}")
	public String getProfile(@PathVariable String name) {
		LOG.info("Looking for Profile for "+ name);
		try {
			Thread.sleep(150);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String profile = profileProperties.getProfiles().containsKey(name)?
				profileProperties.getProfiles().get(name):
				"Profile not found.";
		LOG.info("Profile for "+ name + " : " + profile);
		return profile;
	}
}
