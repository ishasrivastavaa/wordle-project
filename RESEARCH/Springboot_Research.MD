# Research Report

## Spring Boot Setup and Configuration

### Summary of Work  
Kelly and I (Kelsey) researched the basics of how to get a Spring Boot application up and running, focusing on the setup and configuration. We explored Spring Boot’s documentation, as well as followed a tutorial that walked through setting up a simple web application. We were able to also find boilerplate code which included all the files we needed to import to get Spring Boot and Gradle to work. The goal was to understand how to incorporate Spring Boot into our application so that we could connect the frontend to the backend.

### Motivation  
We needed a framework for building Java web applications, and Spring Boot provides this functionality with minimal configuration. Spring Boot is particularly attractive because it simplifies dependency management, offers built-in server support, and makes it easy to start a project quickly with embedded tools like Spring Initializr.

### Time Spent  
- **~60 minutes** reading through Spring Boot’s documentation and going through a code tutorial.  
- **~90 minutes** setting up and troubleshooting initial configurations for the project, including ensuring dependencies were properly managed via Gradle.  
- **~30 minutes** configuring a basic REST controller and testing the embedded server   

### Results  
We successfully created a basic Spring Boot application with talked to our frontend and display information on a port. Using Gradle, we were able to manage dependencies effectively, including adding libraries for testing. Additionally, we became more familiar with Spring Boot’s annotation-based approach, which reduced the manual setup needed for the project.

What still needs to be done is connecting the application to a database, using Hibernate ORM.

### Links  
- [Spring Boot documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)  
- [Spring Boot Initializr](https://start.spring.io/)  
- [Spring Boot video tutorial](https://www.youtube.com/watch?v=vtPkZShrvXQ)  
- [Spring Boot boiler code](https://spring.io/guides/gs/intellij-idea)

