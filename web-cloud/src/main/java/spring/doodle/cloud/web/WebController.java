package spring.doodle.cloud.web;


import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.RequestContextUtils;

@RestController
public class WebController {
	private static final Logger LOG = Logger.getLogger(WebController.class.getName());
	
	private GreetingService greetingService;
	private NameService nameService;
	

	public WebController(NameService nameService, GreetingService greetingService) {
		this.greetingService = greetingService;
		this.nameService = nameService;
	}

	@RequestMapping
	public String index(HttpServletRequest request) {
		String locale = RequestContextUtils.getLocaleResolver(request).resolveLocale(request).toLanguageTag();
		String greeting =  new StringBuilder().append(greetingService.getGreeting(locale)).
				append(" ").append(nameService.getName()).toString();
		LOG.info("Greeting: " + greeting);
		LOG.info("Locale: " + locale);
		return greeting;
	}
}
