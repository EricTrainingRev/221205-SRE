# Planetarium

The Planetarium is a web service that allows users to add planets and associated moons to a central database to map the night sky. Users must register an account to participate, and those who do will be able to associate themselves with the planets and moons they add to the database. Much of the infrastructure has already been created: it is your job to finish writing the implementation code for this application, and to add logging/metric aggregation as well. 

## Key Terminology
- **Project**
  - A software application built for some company/entity
  - Examples
    - Employee paid time off Scheduler
    - Helicopter Navigation System
    - To-Do task manager
- **Sprint**
    - a term used to describe a short period of development work, typically no more than a few weeks of time
- **Minimum Viable Product**
    - a phrase used to describe a project that has the minimum number of features and functionality applied to make the sprint considered successful

## Development Requirements
Each class in the list below has one or more unimplemented methods you will need to complete to achieve MVP requirements:
- Utilities Package
    - ConnectionUtil
- Repository Package
    - UserDao
    - PlanetDao
    - MoonDao
- Services
    - UserService
    - PlanetService
    - MoonService

**NOTE: you can create a main method in each class to test the methods as you work on them, then delete it when you're done with the class**

## SRE Requirements
This is the core of the project: the application as it stands currently has no logging or metric aggregation. It is your job to implement SLF4j and Logback to capture relevant events and their associated data in real time. You will also need to create a script OUTSIDE of the project to aggregate the data to measure your SLIs and achieve MVP requirements. 

### Service Level Objects
- 99.8% of requests should complete successfully within 200 milliseconds

### Service Level Indicators
- Latency
    - you should track how long it takes for the Planetarium app to handle requests made to the system
- Error Rate
    - you should track the percentage of how many http requests return a non-500 status code

**NOTE: you are free to edit the pre-provided methods to add your logging, but be careful of making sweeping changes to the code: edit, don't recreate**

### Service Level Indicator Exposure
You will need to create one or more bash scripts that read the log file of the project and parse the relevant data

# MVP Requirements Rundown
- Development
    - All unimplemented methods should be completed
- SRE
    - Logging should be added to the project
    - One or more scripts should handle measuring your SLIs by reading/parsing the log file

# Stretch Goal
Stretch goals are things to work on ONLY when all MVP requirements have been accomplished: listed below is an optional feature you can add to the project to enhance it further:
- Create a way for the application to return the SLI metrics for the lifespan of the application via an http request
    - This will require you to create your own custom classes and integrate them into the project