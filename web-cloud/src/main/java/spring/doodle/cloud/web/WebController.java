package spring.doodle.cloud.web;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.RequestContextUtils;

import rx.Completable;

@RestController
public class WebController {
	private static final Logger LOG = Logger.getLogger(WebController.class.getName());
	
	private GreetingService greetingService;
	private NameService nameService;
	

	public WebController(NameService nameService, GreetingService greetingService) {
		this.greetingService = greetingService;
		this.nameService = nameService;
	}

	@RequestMapping("/bio")
	public String indexBio(HttpServletRequest request) {
		String locale = RequestContextUtils.getLocaleResolver(request).resolveLocale(request).toLanguageTag();
		
		String greeting =  new StringBuilder()
				.append("BIO: ")
				.append(greetingService.getGreeting(locale))
				.append(" ")
				.append(nameService.getName())
				.toString();
		
		
		LOG.info("Greeting: " + greeting);
		LOG.info("Locale: " + locale);
		return greeting;
	}
	
	@RequestMapping("/nio")
	public String indexNio(HttpServletRequest request) {
		String locale = RequestContextUtils.getLocaleResolver(request).resolveLocale(request).toLanguageTag();
		
		Executor executor = Executors.newCachedThreadPool();
		CompletableFuture<String> futureGreeting = CompletableFuture.supplyAsync(() -> {
			return greetingService.getGreeting(locale);
		}, executor);
		
		CompletableFuture<String> futureName = CompletableFuture.supplyAsync(() -> {
			return nameService.getName();
		}, executor);
		
		CompletableFuture<String> combinedFuture = futureGreeting.thenCombine(futureName, (greeting, name) -> {
			LOG.info("Greeting: " + greeting);
			LOG.info("Locale: " + locale);
			return new StringBuilder()
					.append("NIO: ")
					.append(greeting)
					.append(" ")
					.append(name)
					.toString();
		});
		
		try {
			return combinedFuture.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			return "Failed.";
		}
	}
}
