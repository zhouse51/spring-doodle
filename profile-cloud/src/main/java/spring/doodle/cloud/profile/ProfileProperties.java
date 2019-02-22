package spring.doodle.cloud.profile;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="name")
public class ProfileProperties {
	Map<String, String> profiles;

	public Map<String, String> getProfiles() {
		return profiles;
	}
	public void setProfiles(Map<String, String> profiles) {
		this.profiles = profiles;
	}
}
