
##**User service - REST operations**

The following are the rest operations available to use the user service.  

| Operation	| HTTP action | Description | 
| :----- 	| :---- 	| :---- | 
| Find User by email | GET <***uri authority***>/api/<***version***>/users?email=<***email***> | Success: HTTP Code 200. JSON Response. HTTP Code 404 if user not found |
| Find User by alias name | GET <***uri authority***>/api/<***version***>/users/<***aliasName***> | Success: HTTP Code 200. JSON Response. HTTP Code 404 if user not found |
| Create User | POST <***uri authority***>/api/<***version***>/users | Input: JSON payload. Success: HTTP Code 201. Error: HTTP Code 409, JSON Response |
| Update User | PUT <***uri authority***>/api/<***version***>/users/<***userId***> | Input: JSON payload. Success: HTTP Code 200. Error: HTTP Code 409, JSON Response. HTTP Code 404 if user not found |
| Delete User | DELETE <***uri authority***>/api/<***version***>/users/<***userId***> | Success: HTTP Code 200. JSON Response. HTTP Code 404 if user not found |

***uri authority*** at local could be 'localhost:8089' 

***version*** at local could be 'v1' 
