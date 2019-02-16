package spring.doodle.cloud.name;


import java.util.logging.Logger;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class NameController {
	private static final Logger LOG = Logger.getLogger(NameController.class.getName());
	
	private NameProperties nameProperties;

	public NameController(NameProperties nameProperties) {
		this.nameProperties = nameProperties;
	}

	@RequestMapping
	public String getName() {
		LOG.info("Name: " + nameProperties.getName());
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nameProperties.getName();
	}
}
