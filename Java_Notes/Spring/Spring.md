# Spring

## What is Spring?
Spring is an incredibly popular Java Framework that streamlines the development process and allows you as a developer to focus on solutions and features while not worrying about things like implementation or writing boilerplate code. As a framework, Spring has a lot to offer:
- Inversion of Control (IoC)
    - Spring takes the idea of a framework (code is provided, you just need to supply the data) and takes it to a whole new level. With Spring, you can set up your web service and database connection, and upon doing so, have the majority of your application ready to go: you may need to provide novel code implementation here and there, but Spring will handle the majority of the setup for your application for you.
- Opinionated Framework
    - Spring Boot, the implementation of Spring we will be working with, is known as an "opinionated" framework: it takes the little amount of input Spring normally requires from the developer and "makes decisions" for us. 
- Dependency Injection
    - Spring handles providing the correct dependencies to your objects, further allowing you to focus soley on the features you need to implement
- Spring has a huge support framework
    - there are a plethora of resources available on the web you can utilize if you want to learn more about Spring or if you need help solving a problem. [Baeldung](https://www.baeldung.com/) is a great resources, along with the main [Spring](https://spring.io/) website

## Spring Boot
If Spring is a framework that handles the majority of boilerplate code for you, Spring Boot is the framework implementation that goes even further. As an opinionated framework, Spring boot upon initialization handles most configurations for you. We will be using Spring boot to configure three modules for us: Spring Web, Spring Data, and Spring Acuator.
- Spring Web
    - this is the module that will handle setting up our web server for our application. It also handles mapping our routes to our controller methods, and it even handles crafting the http response body from our java objects
- Spring Data
    - this is the module that will handle creating a connection to our Postgres database and it will handle sending queries and getting their result sets back.
- Spring Actuator
    - this module handles exposing endpoints for our web application that will contain useful metrics for our application. There are performance metrics exposed, but we will focus on the metrics that allow us to observe the state of the application. 