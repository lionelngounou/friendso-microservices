
frienso : social net app to demonstrate the concept of microservices. 
A bit like facebook, the following can be done through the app:
- register as a user 
- create post
- make comments on posts
- connect with other users to make friends
- receive notifications (friends requests, new connections, etc...)
- only see friends posts/comments

!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
Gateway (Bootstrap, HTML, Javascript, Angularjs, URL resolution, rest, Authentication, Authorisation, Grails, Hystrix, Eureka)

!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
User service (rest, mysql, grails/jax/springboot, Authentication, Authorisation, Eureka)
	[#] User [id, app_id, firstname, lastname, email/username, password, dateCreated, lastLogin, active]

!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
Posts/Comments service (rest, nodejs, Eureka)
	[#] Post [id, user_id, content, dateCreated, deleted]
	[#] Post_Comment [id, post_id, user_id, user_firstname, content, dateCreated, deleted]
	[~] Listen to user [firstname] change event and update Post_Comment [user_firstname]
	[~] Listen to user [deleted] event

!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
Friends service - Connections (rest, neo4j, Eureka)
	[#] Friend [from_user_id, to_user_id, dateCreated, accepted, dateAccepted, deleted, dateDeleted]

!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
Notifications service - (rest, redis, messaging receiver, nodejs)
	[#] Notification [id, user_id, content, viewed, dateCreated]
	[~] Listen to friends requested event
	[~] Listen to friends accepted event
	[~] Listen to post_comment created event

!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
Email service 
	[#] email [id, to_email, subject, content, dateSent]
	[~] Listen to user created event

