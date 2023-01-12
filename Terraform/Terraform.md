# Terraform
Terraform is a tool that allows for creating "Ingfrastructure as Code", specifically for your cloud infrastructure. We have used similar tools before (think dockerfiles, Jenkinsfiles, and manifests), but all of the previous tools required some sort of cloud infrastructure to be in place before they could be used. You needed a Kubernetes controlled cluster for your manifest files to be effective, and you needed docker images created from your dockerfiles for your application to be deployed into the cluster. With Terraform, we can actually manage the state of the cloud resources that compose the cluster (the nodes, the vpc, and rds instances deployed within the same vpc, etc). 

## Terraform Basics
There are three main sections to any Terraform configuration (these can all be in the same file, or seperated)

### Terraform Block
This is where Terraform specific information will be placed, typically you will see a "required_providers" section here: this tells Terraform what cloud providers (if any) you are working with

### Provider Block
This is where you put the configurations necessary for interacting with the cloud provider: typically this will include some sensitive information for giving terraform permission to interact with the cloud provider

### Resource Blocks
Resource blocks are used to define individual components of a deployment: these can be things like VPCs, servers, 3rd party resources, etc.

### Terraform workflow
- terraform init
    - this tells terraform to start tracking changes you make to your terraform scripts
- terraform plan
    - this tells terraform to start checking the current state of the infastructure that terraform is managing, and if any of the current state does not match the terraform files that are read, then terraform willl note that it needs to change the current state to match the newly desired state
        - if a + is next to a resource it is going to be added to the state of the resources being managed by terraform
        - if a - is next to a resource then it is going to be removed
    - you can add the -out flag and give a location for where you want to "plan" for the state of the terraform managed deployment to be saved. If you don't include -out then the plan does not actually get saved, you just get to see what would happen if you ran the apply command
- terraform apply {plan location}