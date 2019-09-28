<snippet>
  <content><![CDATA[

# ${1:InBlogApplication}
This is the backend part of the InBlogApplication using spring boot, H2 db and in memory redis. All API Endpoints are here.

## Installation
To build the application, run : gradle build
To run the application, run : gradle build bootRun
## Usage
1. Sign up API: localhost:7071/p/signup
   Method : POST
   Body sample : {
	                  "userName": "joy1234",
	                  "fullName": "joy das",
	                  "password": "joy1234",
	                  "email" : "joydas01ei@gmail.com",
	                  "isAdmin": "true"
                 }

2. Login API: http://localhost:7071/p/login
   Method : POST
   Body Sample : {
                    "username": "joy1234",
                    "password": "joy1234",
                    "isAdmin": "true"
                 }
 
3. Get All Users API: localhost:7071/a/users?isActive=true
   Method : GET
   Header : { token: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbWl0MTIzNCIsImp0aSI6IjQiLCJpc3MiOiJodHRwczovL2luYmxvZy5jb20iLCJpYXQiOjE1NjkzOTE0MzEsImV4cCI6MTU2OTY5MTQzMX0.J_pZ2RIvS6A7uEk1-7Z5WAxfvtwHAocfk0Swk-qFrAeGmYsP6OrgDmRcV1vZvcBqKKcK_NlYyJ6QKR6qB64s2Q}

4. Change User Status API : localhost:7071/a/user/change-status
   Method : POST
   Header : { token:eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbWl0MTIzNCIsImp0aSI6IjQiLCJpc3MiOiJodHRwczovL2luYmxvZy5jb20iLCJpYXQiOjE1NjkzOTE0MzEsImV4cCI6MTU2OTY5MTQzMX0.J_pZ2RIvS6A7uEk1-7Z5WAxfvtwHAocfk0Swk-qFrAeGmYsP6OrgDmRcV1vZvcBqKKcK_NlYyJ6QKR6qB64s2Q}
   Body sample : {
		    "username": "iftiaz1234",
	            "isActive": "false"
		 }
   
## Contributing
1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D
## History
TODO: Write history
## Credits
TODO: Write credits
## License
TODO: Write license
]]></content>
  <tabTrigger>readme</tabTrigger>
</snippet>
