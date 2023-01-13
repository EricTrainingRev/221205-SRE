# Jenkins Study Guide
Jenkins is automation software that can handle building, testing, and deploying applications for a DevOps pipeline in either a Continuous Delivery or Deployment practice. This software can handle actions such as building an app from source code, testing the code, building a docker image, even running kubectl commands. These actions and more are handled by Jenkins "jobs", which represent single DevOps pipelines. 

One of the benefits of using Jenkins is how configurable it is: Jenkins supports a myriad of plugins that allow you to integrate tools like kubernetes, docker, cloud providers, and more.

## Jenkins Agent
Jenkins makes use of "agents" to handle pipeline actions, with one or more agents equipped with the tools necessary to perform all pipeline actions. For instance, if a pipeline needs to build an app from source code, test it, build an image from the app, and push it to docker hub, you could have one agent with maven and java installed build/test the app, then send the code/app over to a second agent with docker to build/push an image of the application. Coordinating between various agents is handled by a primary Jenkins server.

## Jenkinsfile
You can configure Jenkins jobs in the Jenkins UI, but Jenkins also supports using "Jenkinsfile"s to facilitate jobs. These files are written with Groovy syntax and can be saved in a source code repository alongside the rest of the code and configuration, allowing for easy tracking of changes to the pipeline. Jenkinsfiles are how Jenkins supports the Configuration as Code model.
```groovy
// the pipeline information goes inside the "pipeline" section
pipeline{
    // the agent section tells Jenkins what agent is needed to complete the job
    agent any

    // the environment section lets you set environment variables for the job
    environment{
        KEY = "value"
    }
    // you can break pipelines down into individual stages
    stages{
        // each individual stage needs to be created
        stage("stage name"){
            // instructions for the pipeline go here
            steps{
                // if an agent needs to run commands in specific containers you can indicate which container should
                // run the commands
                container("name"){
                    // Jenkins supports running commands via the sh command, but also supports other built-in
                    // commands
                    sh "unix command"
                    // a script section can be used when working with plugin provided functions
                    script{
                        docker.build("image name")
                    }
                }
            }
        }
    }
}
```