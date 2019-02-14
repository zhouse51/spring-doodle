Spring Doodle is a personal project to learn Spring Boot related technology.

Cloud Projects
Cloud projects are simple demo to the Spring Cloud.
The original demo is from: https://github.com/ryanjbaxter/beginners-guide-to-spring-cloud

Demo Services:
	Web: A front end service, calling Name service and Greeting service and return the combine the result.
	Name: A service returns a name base on the configuration. Simulates 300ms process time.
	Greeting: A service returns a greeting word depends on the browser language setting. Simulates 500ms process time.
	
Demo Features includes:
	Euerka:        Server registry and discovery. Include server and client.
	Configuration: central configuration. Include server and client. Configured to use Github repository stores the configuration files.
	Zipkin/Sleuth: tracing service, central log
	Zuul:		   routing/filting, routing perform like nginx (reverse proxy)
	Ribbon:        work with Euerka to enable the load balance in the server.

	Hystrix:       circet breaker
