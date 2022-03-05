# Open Close microservice solution

## Architecture

[![architecture diagram](https://github.com/Miccia/open-close-ms/blob/1.0dev/diagram.png "architecture diagram")](https://github.com/Miccia/open-close-ms/blob/1.0dev/diagram.png "architecture diagram")
The solution is composed of two spring-boot application:
- frontend-service
- openclose-service
Which communicates via a kafka topic and expose REST apis.
Everything id dockerized with a 1 publisher 2 subscriber configuration

## Execution

to run the solution you need to: 
`docker compose up --build`
inside the docker/all folder, the frontend addresses are: http://localhost:8081/ and http://localhost:8082/

## Testing

to test the functionality you can access the swagger-ui  under http://localhost:PORT/swagger-ui/index.html#/ or use the justeat.postman_collection.json
where PORT is:
8080 for the backend
8081 & 8082 for the frontend
the microservices initial data status is:


| id  | name | description | status |
| ------------- | ------------- | ------------- | ------------- |
|first|IGio|Korean|open|
|second|Biondo Grano Roma|Pizza - Hamburger|closed|
|third|Dumpling Bar - Meucci|Bao and Dumplings|open|
|fourth|Trapizzino Testaccio|Panini - italiano|open|
|fifth|Veri Peri Peri|Indian fried chicken|closed|
|sixth|Instanbul Kebab|kebab piadine|open|
|seventh|Yo Fusion|Sushi Bar|closed|

## Frontend-service

this service implements a kafka listener to receive restaurants status change and update it's internal status.
It exposes a REST api to get a list of restaurants filtered by status and a React front-end
that displays a list of all open restaurants

## Backend-service

this service implements a kafka producer to publish restaurants status change.
It exposes a REST api to change a restaurant status and get the status by id

## Scalability

horizontal scalability is achieved by creating subscriber microservices with different group ids, by doing this we ensure that each message present on the topic is consumed by all the subscribers

## Issues

- Data 
Using dedicated data sources for the consumers does not ensure proper data replication or initialization for every consumer, to solve this we 
could let the consumers interrogate the backend when it does not have data on a restaurant in his memory but this would make the consumer depends
on the backend availability
another solution would be to use a worker consumer that updates a db shared by the front-end microservices, by using the worker we prevent 
possible concurrency problem in the case of multiple front-end services. 

## Development issues

- Dockerizing 
	I encountered an ugly issue when making the multistage Dockerfile for the front-end service, to pack the web application I used com.github.eirslett:frontend-maven-plugin after a couple hours I found that the problem was in the image used for the build stage:
	Apparently [(issue)](https://github.com/eirslett/frontend-maven-plugin/issues/633 "(issue)") linux Alpine miss the glibc libraries and that prevents the plugin to work properly, it also seems that the majority
	of the maven docker image hosted on dockerhub also uses Alpine, I solved this by using this image:'kemixkoo/debian-maven', however, creating a custom image on a private registry would be preferable.
	

- Testing kafka
	I struggled a bit trying to mock a broker to run integration tests, there are solutions for this but I think that a complete integration
	test is out of scope in this case, I then decided to mock all the integration layer by using 
	@ConditionalOnProperty in the configuration classes and @MockBean for the consumers and producer to run tests against the REST service layer
