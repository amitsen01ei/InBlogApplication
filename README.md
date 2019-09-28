<snippet>
  <content>

## InBlogApplication
This is the backend part of the InBlogApplication using spring boot, H2 db and in memory redis. All API Endpoints are here.

## Installation
To build the application, run : `gradle build`
To run the application, run : `gradle build bootRun`
## Usage
1. Sign up API: localhost:7071/p/signup <br />
   Method : POST <br />
   Body sample : { <br />
	                  "userName": "joy1234", <br />
	                  "fullName": "joy das", <br />
	                  "password": "joy1234", <br />
	                  "email" : "joydas01ei@gmail.com", <br />
	                  "isAdmin": "true" <br />
                 } <br />
<br />
2. Login API: http://localhost:7071/p/login <br />
   Method : POST <br />
   Body Sample : { <br />
                    "username": "joy1234", <br />
                    "password": "joy1234", <br />
                    "isAdmin": "true" <br />
                 } <br />
 <br />
3. Get All Users API: localhost:7071/a/users?isActive=true <br />
   Method : GET <br />
   Header : { token: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbWl0MTIzNCIsImp0aSI6IjQiLCJpc3MiOiJodHRwczovL2luYmxvZy5jb20iLCJpYXQiOjE1NjkzOTE0MzEsImV4cCI6MTU2OTY5MTQzMX0.J_pZ2RIvS6A7uEk1-7Z5WAxfvtwHAocfk0Swk-qFrAeGmYsP6OrgDmRcV1vZvcBqKKcK_NlYyJ6QKR6qB64s2Q} <br />
<br />
4. Change User Status API : localhost:7071/a/user/change-status <br />
   Method : POST <br />
   Header : {  token:eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbWl0MTIzNCIsImp0aSI6IjQiLCJpc3MiOiJodHRwczovL2luYmxvZy5jb20iLCJpYXQiOjE1NjkzOTE0MzEsImV4cCI6MTU2OTY5MTQzMX0.J_pZ2RIvS6A7uEk1-7Z5WAxfvtwHAocfk0Swk-qFrAeGmYsP6OrgDmRcV1vZvcBqKKcK_NlYyJ6QKR6qB64s2Q} <br />
   
   Body sample : { <br />
		    "username": "iftiaz1234", <br />
	            "isActive": "false" <br />
		 } <br />
<br />
5. Submit Post API : localhost:7071/a/blog/submit-post <br />
   Method : POST <br />
   Header : { token:eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbWl0MTIzNCIsImp0aSI6IjQiLCJpc3MiOiJodHRwczovL2luYmxvZy5jb20iLCJpYXQiOjE1NjkzOTE0MzEsImV4cCI6MTU2OTY5MTQzMX0.J_pZ2RIvS6A7uEk1-7Z5WAxfvtwHAocfk0Swk-qFrAeGmYsP6OrgDmRcV1vZvcBqKKcK_NlYyJ6QKR6qB64s2Q} <br />
   Body Sample : { <br />
		    "title": "title1234", <br />
		    "subtitle": "test subtitle", <br />
		    "body": "test body", <br />
		    "author": "test author" <br />
		 } <br />
<br />
6. Get Blog Posts API : localhost:7071/a/blog/posts?status=PENDING&page=0&size=1 <br />
    Method : GET <br />
    Header :  { token:eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbWl0MTIzNCIsImp0aSI6IjQiLCJpc3MiOiJodHRwczovL2luYmxvZy5jb20iLCJpYXQiOjE1NjkzOTE0MzEsImV4cCI6MTU2OTY5MTQzMX0.J_pZ2RIvS6A7uEk1-7Z5WAxfvtwHAocfk0Swk-qFrAeGmYsP6OrgDmRcV1vZvcBqKKcK_NlYyJ6QKR6qB64s2Q} <br />
    <br />
<br />
7. Delete Post API : localhost:7071/a/post/delete <br />
   Method : DELETE <br />
   Header : { token:eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbWl0MTIzNCIsImp0aSI6IjQiLCJpc3MiOiJodHRwczovL2luYmxvZy5jb20iLCJpYXQiOjE1NjkzOTE0MzEsImV4cCI6MTU2OTY5MTQzMX0.J_pZ2RIvS6A7uEk1-7Z5WAxfvtwHAocfk0Swk-qFrAeGmYsP6OrgDmRcV1vZvcBqKKcK_NlYyJ6QKR6qB64s2Q} <br />
  Body Sample : { <br />
  		    "postId":"1", <br />
		    "postAuthor": "joydas1234" <br />
		} <br />
]]></content>
  <tabTrigger>readme</tabTrigger>
</snippet>
