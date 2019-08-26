Spring Doodle is a personal project to learn Spring Boot related technology.

Cloud Projects
Cloud projects are simple demo to the Spring Cloud.
The original demo is from: https://github.com/ryanjbaxter/beginners-guide-to-spring-cloud
More references:
	https://blog.csdn.net/forezp/article/details/70148833

Demo Services:
	Web: A front end service, calling Name service and Greeting service and return the combine the result.
	Name: A service returns a name base on the configuration. Simulates 300ms process time.
	Greeting: A service returns a greeting word depends on the browser language setting. Simulates 500ms process time.
	
Demo Features includes:
	Euerka:			Server registry and discovery. Include server and client.
	Configuration:	central configuration. Include server and client. Configured to use Github repository stores the configuration files.
	Zipkin/Sleuth:	tracing service, central log
	Zuul:			routing/filting, routing perform like nginx (reverse proxy)
	Ribbon:			work with Euerka to enable the load balance in the server.
	Feign:			load balance
	Hystrix:		circuit breaker
	Bus:			in this demo, I configured to use rabbitMQ as message queue service. Configure the config-server to broadcast the configuration updated message.
					- start rabbitMQ server. Demo runs a docker image
					- update configuration value in the git repository
					- reload the configuration in config-server
					- call <POST>/actuator/bus-refresh to the config-server to broadcast the message througth the MQ
					
Running sequence:
1. start rabbitMQ docker container
2. start eureka-server port: 8761 http://localhost:8761/
3. start config-server x2 port: 8888, 8889
4. start zipkin-server port: 9411
5. start greeting x2 port: 9090, 9091
6. start naming x2 port: 7070, 7071
7. start web x2 port: 8080, 8081
8. start zuul port 8769

Test:
1. circuit break: stop all name servrers. 
2. central configuration: update configure value, commit to git, call <POST>/actuator/bus-refresh to the config-server
