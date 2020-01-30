<div align="center">
 ![StackRoute Logo](screenshots/sr-logo.png)
</div>

## Seed code - Boilerplate for step 6 - NewzApp Assignment

### Assignment Step Description

In this case study NewzApp Step 6, we will implement and deploy the application developed in step 5 as set of  microservices communicating with each other.  The microservices should communicate with each other using Netflix-Zuul-API-Gateway and should be registered on nexflix-Eureka-naming-Server.
You should also use Netflix-Feign for microsesrvice communication and Netflix-Ribbon for client side load balancing .

In this step, we will create this application in four independently deployed  Micro Services. 
    
        1. AuthenticationService
        2. NewsService
        3. NewsSourceService
        4. UserProfileService

Filter using JWT should be implemented in the Zuul API Gateway service. It should not be added as part of the Authentication,News,NewsSource and UserProfile Services.

Containerization of all the microservices using Docker needs to be implemented as part of this assignment. For each services, Dockerfile has to be updated with the required instructions.
All the images to be run using the docker-compose.

Swagger needs to be implemented in all the services.

 ## Steps to be followed for implementing  and deploying application as Micro Services 
 
 ### Select following port to run different MicroServices

### Application		    -		Port
AuthenticationService	- 		8082
NewsService			    -		8081
NewsSourceService		-		8083
UserProfileService  	-	    8084 
Netflix Eureka Naming Server -	8761 
Netflix Zuul API Gateway Server	- 8765 

=======================================

### You should set following propertes for Microservices
### application.propertes for all Services except Eureka server 
========================

  spring.application.name= {{name of the application }}

  server.port=  {{port number suggested above}}

  eureka.client.service-url.default-zone=http://localhost:8761/eureka


  #################################

  To run application on multiple port 
  In Eclipse IDE ->  Right Click application  -> RunAs => Run Configuration 
  VM arguments 
   -DServer.port={{portnumber}}

  --------------------------------------
  Project Dependencies
  ============================
  Project should be created with Spring Initializer (start.spring.io) with follwing dependency
  Web, DevTools, Feign, Ribbon, EurekaDiscovery , Zuul
  JPA can be taken where persistance is required. 
  Note that  For project EurekaNaming server 
  Port:8761
  Spring Initializer dependency is Eureka Server and not Discovery 


### Steps to be followed:

    Step 1: Clone the boilerplate in a specific folder on your local machine and import the same in your eclipse STS.
    Step 2: Go thru the readme.md file and implement the code for AuthenticationService and run the test cases.
    Step 3: Go thru the readme.md file and implement the code for NewsService and run the test cases.
    Step 4: Go thru the readme.md file and implement the code for NewsSourceService  and run the test cases.
    Step 5: Go thru the readme.md file and implement the code for UserProfileService and run the test cases.

### Project structure

The folders and files you see in this repositories, is how it is expected to be in projects, which are submitted for automated evaluation by Hobbes

    Project
	|
	├── AuthenticationService                   // This is the microservice for User Authentication
	├── NewsService                             // This is the microservice of News   
	├── NewsSourceService                       // This is the microservice of Newssource   
	├── UserProfileService                      // This is the microservice of UserProfile   
	├── netflix-eureka-naming-server            // This is for Netflix-eureka-naming-server   
	├── netflix-zuul-api-gateway                // This is for Netflix zuul api gateway
	├── .gitignore			            // This file contains a list of file name that are supposed to be ignored by git 
	├── .gitlab-ci.yml			        // This file contains the configuration for CI 
	├── .project			            // This is automatically generated by eclipse, if this file is removed your eclipse will not recognize this as your eclipse project. 
    ├── README.md			            // This is a readme file about the NewzApp step5 assignment. 
    ├── docker-compose.yml	            // yml configuration file to define and run the multi-container docker application.
	└── pom.xml 			            // This is the parent POM, which holds all the microservice projects.

> PS: All lint rule files are by default copied during the evaluation process, however if need to be customizing, you should copy from this repo and modify in your project repo


#### To use this as a boilerplate for your new project, you can follow these steps

1. Clone the base boilerplate in the folder **assignment-solution-step6** of your local machine
     
    `git clone https://gitlab-<org abbreviation>.stackroute.in/stack_java_newz/newz-step6-boilerplate.git assignment-solution-step6`

2. Navigate to assignment-solution-step6 folder

    `cd assignment-solution-step6`

3. Remove its remote or original reference

     `git remote rm origin`

4. Create a new repo in gitlab named `assignment-solution-step6` as private repo

5. Add your new repository reference as remote

     `git remote add origin https://gitlab-<org abbreviation>.stackroute.in/{{yourusername}}/assignment-solution-step5`

     **Note: {{yourusername}} should be replaced by your username from gitlab**

5. Check the status of your repo 
     
     `git status`

6. Use the following command to update the index using the current content found in the working tree, to prepare the content staged for the next commit.

     `git add .`
 
7. Commit and Push the project to git

     `git commit -a -m "Initial commit | or place your comments according to your need"`

     `git push -u origin master`

8. Check on the git repo online, if the files have been pushed

### Important instructions for Participants
> - We expect you to write the assignment on your own by following through the guidelines, learning plan, and the practice exercises
> - The code must not be plagirized, the mentors will randomly pick the submissions and may ask you to explain the solution
> - The code must be properly indented, code structure maintained as per the boilerplate and properly commented
> - Follow through the problem statement shared with you

### MENTORS TO BEGIN REVIEW YOUR WORK ONLY AFTER ->
> - You add the respective Mentor as a Reporter/Master into your Assignment Repository
> - You have checked your Assignment on the Automated Evaluation Tool - Hobbes (Check for necessary steps in your Boilerplate - README.md file. ) and got the required score - Check with your mentor about the Score you must achieve before it is accepted for Manual Submission.
> - Intimate your Mentor on Slack and/or Send an Email to learner.support@stackroute.in - with your Git URL - Once you done working and is ready for final submission.


### Further Instructions on Release

*** Release 0.1.0 ***

- Right click on the Assignment select Run As -> spring boot app to run your Assignment.
- Right click on the Assignment select Run As -> JUnit Test to run your Assignment.