# AWS

## What is AWS
Amazon Web Services is Amazon's cloud service provider. Any time you hear someone talking about "the cloud" in the software field they are talking about hardware/software that someone else provides over the internet. AWS has become one of the premier cloud providers in modern times alongside Microsoft Azure and Google's Google Cloud Platform.

## AWS RDS
One of the services AWS provides is their Relational Database Services (RDS). Using this service we can create relational databases and persist our information in them. We will be using AWS RDS in your P0 and beyond to persist information. For training we will be using a Postgres database provided by AWS RDS.

## AWS EC2 & EBS
Amazon's Elastic Cloud Compute (EC2) service provides users with virtual machines (windows or unix based) of varying degrees of computing power and memory. Instead of having to acquire your own hardware you can requisition Amazon's and pay them a fraction of what it would cost to acquire the hardware yourself. There are many benefits of using this service:
- autoscaling
    - Amazon can provide more memory/computing power if your application requires it (think heavy amounts of traffic) and conversely, it can scale down the resources provided in order to lower your overall costs
- security
    - by setting up robust security groups and Identity and Access Management(IAM) roles you can be confident your application is running in a safe and secure environment
    - also, due to not hosting the hardware yourself, you do not need to pay for any physical security: Amazon handles this
- persistent storage
    - your data can be saved in persistent storage called Elastic Block Storage (EBS), so even if you shut down your virtual machines for a while, when you are ready to spin them back up you can load up your data saved in an EBS volume and start working right where you left off
- Accessibility control
    - your instances can be networked in your Virtual Private Cloud (VPC), and you can determine whether you wish for the instance to be public facing or not
- AMIs
    - There are a host of preconfigured Amazon Machine Images (AMIs) that provide pre-configured hardware and software options. This allows you to choose the right operating system and pre-loaded software to fit your needs