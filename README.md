a. Brief explanation for the software development principles, patterns & practices being
applied:
	- I complete the project in 7 days, analyze and design 1 day, code 6 days (2 hours / day). A total of 14 hours.
	- Patterns & practices being: Factory pattern, OOP, Solid 
b.Brief explanation for the code folder structure and the key Java libraries and frameworks
being used:

 ![alt text](https://i.ibb.co/kmn6ZLn/Capture.png)
c. All the required steps in order to get the application run on local computer:
- Clone all project in github, and build steps in order with : "mvn clean install": commons-jwt, commons-phh, authentication-service, product-service, order-service
- Download kafka: https://kafka.apache.org/downloads and install in local.
 	+ Run zookeper: zookeeper-server-start.bat ..\..\config\zookeeper.properties
	+ Run kafka: kafka-server-start.bat ..\..\config\server.properties
	+ Create topic: kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic order-product
	+ Check list topic: kafka-topics.bat --list --zookeeper localhost:2181
- Install mongoDb: https://www.mongodb.com/try/download/community
- The last we'll run three project: authentication-service, product-service, order-service and we have swagger api:
 	+  product-service: http://localhost:8080/product-service/swagger-ui.html
	+  authentication-service: http://localhost:8081/authen-service/swagger-ui.html
	+  order-service: http://localhost:8082/order-service/swagger-ui.html
d. Full CURL commands to verify the APIs: video demo: https://youtu.be/Q7QUkgY80_g
	
	



