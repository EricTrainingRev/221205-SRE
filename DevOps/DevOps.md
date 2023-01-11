# What is DevOps
We have learned about many different teams that help to create a software product:
- Dev Team
    - these are the people who work on the product directly: they create the Repository, Serivce, and API layers of the application, the update the code base and add on new features, etc
- SRE team
    - these are the people who monitor the application and associated software that is deployed with the application. These people do things like monitoring SLIs and alerting the proper people when SLIs indicate that SLOs either are not being met, or the SLO is at risk. SREs are also the first on the scene when an incident occurs, and they are the ones who start/end a postmortem to try and avoid/mitigate such incidents in the future
- Ops team
    - If the dev team works on the product, and the SRE team monitors the product, the Ops team is responsible for handling the "pipeline" the product goes through to get from the dev source code to the production environment where the end user can make use of the application. They are also in charge of making sure the necessary resources for the app to work are present (RDS instance for the app to connect to, cluster for the app to be deployed in, etc).

"DevOps" is a name given to the practice of combining devlopment and ops practices into a single, streamlined process that is ideally as automated as possible.

# DevOps Steps
There are 5 steps to the DevOps practice:

1. Source Code Control
    - this step involves storing your code and updating your code in a central location. Ideally this central location should also provide some means of version control (think Github).
2. Building/Testing Automation
    - this step involves building your application and running any necesary tests to ensure your code works
3. Deploying to staging
    - this step involves sending the built/tested application to a staging enviornment
    - this "staging" environment is where you can perform final testing on the application
4. System Testing
    - this step involves the actual testing of the application
        - Acceptance testing
        - System Testing
        - End to End testing
        - etc
    - the key to recognize is that final testing that was unable to be accomplished in step 2 happens in this fourth step
5. Deployment of Build
    - this step involves taking the fully tested application and sending it to the production environment, thus allowing end users to interact with the application

# DevOps in Agile Methodology
In Agile methodologies (like Scrum) that make use of DevOps practices there are a few terms that are used to describe what levels of DevOps automation/practice is being used:

- *Continuous Inegration*
    - This is the practice of consistently merging code into a central repository that helps to ensure everyone involved in the process is working with the same up to date source code
    - this practice also involves code reviews before allowing new work to be merged into the main source code location (like a main branch of a git repository)
- *Continuous Delivery*
    - this is the practice of automating as much as is reasonable of steps 2-4 of the DevOps process
    - Continuous Delivery does not require that you automate EVERYTHING in the devops process, as this would be an unreasonable demand on many companies. The ideal is to automate as much of the DevOps process as is reasonable for your situation. This will depend on factors such as resource availability, team size, scope of your aplpication, etc.
    - because of this, the term is somewhat nebulous, so if you are ever told your team practices Continuous Delivery you should ask followup questions to better understand the DevOps pipeline you are going to work with
- *Continuous Deployment*
    - this is the all encompasing DevOps practice: if you can automate your entire DevOps pipeline reasonably then you are practicing Continuous Deployment
    - when things don't go wrong, a Continuous Deployment practice allows for the entirety of the pipeline to be automated, and a good Continious Deployment pipeline will have safeguards in place so that if something does go wrong the pipeline can handle it and ensure that faulty code does not get sent to the production enviornment and exposed to end users