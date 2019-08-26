package spring.doodle.cluod.greeting;


import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class GreetingController {
	private static final Logger LOG = Logger.getLogger(GreetingController.class.getName());

	private GreetingProperties greetingProperties;

	public GreetingController(GreetingProperties greetingProperties) {
		this.greetingProperties = greetingProperties;
	}

	@RequestMapping("/{languageCode}")
	public String getGreeting(@PathVariable String languageCode){
		LOG.info("Language Code: " + languageCode );
		LOG.info("Greeting: " + greetingProperties.getGreetings().get(languageCode.toUpperCase()));
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return greetingProperties.getGreetings().getOrDefault(languageCode.toUpperCase(), greetingProperties.getGreeting());
	}

	@RequestMapping("/")
	public String getGreeting(){
		LOG.info("Greeting: " + greetingProperties.getGreeting());
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return greetingProperties.getGreeting();
	}
}
