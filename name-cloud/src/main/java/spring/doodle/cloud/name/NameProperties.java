package spring.doodle.cloud.name;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class NameProperties {
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
