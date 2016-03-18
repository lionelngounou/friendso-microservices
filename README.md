##**friendso - microservices demo app**

This is a social network app to demonstrate the concept of microservices. 
A user should be able to benefit from the following features :

 - register as a user 
 - create posts
 - make comments on posts
 - connect with other users to make friends
 - receive notifications (friends requests, new connections, etc...)
 - only see friends posts/comments
 - etc...

The app would be broken into components/services following the **API Gateway pattern**. 
Communication/Interactions between services and integration would be handled through **service calls** (i.e REST) and **messaging** (i.e ***MQ)

For a start, the app would be broken into the ***User service,*** the ***Post/Comment service***, the ***Friend/Connection service***, and the ***Notification service***.  
From a client/user perspective, the services would be invoked or called through the API gateway, which would also handle page/ui rendering and security (authentication, authorisation). 
The aim is not only to demonstrate the ability to partition the app, but also the fact it can adopt resilience, and can be implemented with various programming languages & frameworks, data stores (SQL & NoSQL), and communication/integration mechanisms. 

####**Service stacks**
| Service	| *Stack*	| 
| :------- 	| :---- | 
| User service		| *```Java, Spring Boot, REST, Eureka, MySQL```* | 
| Post/Comment service	| *```NodeJs, ExpressJs, REST, Eureka, MongoDB```* | 
| Friend/Connection service	 | *```Java 8, Spring Boot, REST, Eureka, Neo4j```* | 
| Notification service		| *```Nodejs, ExpressJs, REST, Eureka, Redis```* | 
| 	|  | 
| API Gateway & UI	| *```Grails, Spring Security, Netflix Hystrix, Eureka, JQuery, Javascript, Bootstrap CSS```* | 

####**Messaging**
|Topic | Publisher | Consumer| 
|:--|:--|:--|
|User name(s) updated | User service| Post/Comment service (update names), Friend/Connection service (update names)|
|New post | Post/Comment service| Notification service (notify friends)|
|New comment on post | Post/Comment service| Notification service (notify owner of post, and all commentators on post)|
|New friend/connection | Friend/Connection service| Notification service (notify all friends)|