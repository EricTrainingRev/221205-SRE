# Kubernetes: The Need
Good news! A few investors have taken an interest in the Planetarium, and now the company is going global! There are a few things that need to be considered before this dream can become a reality:
- Things are constantly updating: how are you going to ensure that all users get the same updates at the same time?
- Your application needs to be reliable
    - Availability needs to be high
    - There needs to be failsafes in case availability drops
- There needs to be room for growth
- There needs to be room for feature additions


# Kubernetes: The Solution
Kubernetes is a system that is capable of handling all of these requirements and more for us.
- Kubernetes makes use of containers natively in order to run applications
    - aka it is very easy to deploy software using kubernetes
- Kubernetes has built in systems/integrations for maintaining high levels of availability
    - Kubernetes supports autoscaling (vertical & horizontal)
    - Kubernetes supports loadbalancing
    - Kubernetes makes constant health checks to ensure your applications are running correctly
        - if a problem is discovered Kubernetes will automatically take action in order to correct any mistakes, and if necessary, destroy/recreate containers
- Kubernetes supports your infrastructure using Infrastructure as Code (IaC)
    - Most of Kubernetes is created/written using IaC
        - yml or json files
    - You can update your configurations on the fly and Kubernetes will adjust itself accordingly

# Kubernetes: Infrastructure
Kubernetes is really a bunch of smaller moving parts that make a cohesive whole: there are a few key pieces you need to be aware of
- Cluster
    - The Cluster is where all the work that Kubernetes is doing is localized. Any virtual machines, applications you deploy, auxilary software you use, etc: all of this is located in the "cluster"
    - Clusters are handled by something called the **Control Plane**: this is the software you interact with to tell Kubernetes what you want, and it is the tool that manages the cluster
    - The Contrl Plane is able to interact with nodes inside the cluster via the node's kublet
        - kublets are software attached to nodes that handle incoming control plane messages
- Node
    - These are the VMs that Kubernetes uses to host/run your application and auxilary software. 
        - Some cloud providers place the Control Plane in an isolated node away from the cluster
- Pod
    - These are individual bits of computing resources that host/run your containerized software
        - all containers are run inside of pods, typically one container per pod
        - you can run multiple containers within a pod, but it is not common
            - this is due to the potential for pods needing to be reset for a variety of reasons: think updating an image or reseting a broken application. If a pod needs to be reset due to a faulty app, but there are two apps running in the pod, both will be reset despite only 1 needing to do so.
- Service
    - These are tools that help to manage communication between resources within the cluster and to manage interactions with people and resources outside of the cluster
        - services can help manage spreading traffic between instances of an app
        - services can also be used to handle incoming requests to the cluster
            - these external services (typically load balancers) can also directly send external requests to the
            applications that are expecting them

# Kubernetes: Imperative vs Declarative
The difference between an imperative and declarative system is in HOW the system is informed about what you want. In a Imperative system:
- command equals action
    - scaling your deployment from 3 to 10 pods is typicall an imperative action: you would give the actual command to Kubernetes and it would adjust itself accordingly
- you are telling Kubernetes exactly what to do in an imperative system
    - Kubernetes will respond to your kubectl commands
- you will typically only use imperative commands when you are debugging/handling an incident
    - upon finishing your work you should undo your imperative changes to allow the system to manage its own intended/current state

In a Declarative system:
- commands equal end statements
    - updating a deployment from an old image to a new one can be done declaratively by updating a configuration file and then applying that new configuration to the cluster
- in Kubernetes the declarative systems creates the "intended state" of the cluster
    - you use configuration files to indicate your intedned state for the application, and once Kubernetes reads these files it updates itself accordingly to change (if necessary) its current state to match the intdended state
- this is the default way Kubernetes is inteded to be used: you use configurations to tell Kubernetes what you want the intended state to be, and then Kubernetes works to change its current state to match your inteded state