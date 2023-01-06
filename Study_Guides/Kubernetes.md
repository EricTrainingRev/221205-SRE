# Kubernetes Study Guide

## What is Kubernetes? [docs](https://kubernetes.io/docs/concepts/overview/)
Kubernetes is a software solution for "Container Orchestration." It provides the means of managing containers on a large scale, as well as tracking them. This service is typically used with a cloud provider like Azure, AWS, or GCP. By combining Kubernetes with cloud services you end up with a powerful service that provides significant levels of automated service for you:
- Service Discovery
    - can control how you expose your containers to end users
- Load Balancing
    - can distribute network traffic so the deployment is stable
- Storage Orchestration
    - can mount storage systems of your choice:
        - local storage
        - cloud storage
    - can automate rollouts and rollbacks
    - can set desired states and Kubernetes will change the actual state to match the desired state you determine
- Automatic Bin Packing
    - you can tell Kubernetes the physical requirements (think cpu and ram) each container in a cluster needs and it will adjust your nodes to try and optimize your resources
- Self Healing
    - can restart containers that fail
    - can replace containers
    - can kill containers that don't respond to user defined health checks
    - will not adverstise nodes to clients till they are ready
- Secrets & Security
    - Can store/manage sensitive information
    - Can deploy/update secrets & app configurations without rebuilding container images

## Kubernetes: Clusters & Nodes
Clusters are collections of virtual machines (called nodes) that are all managed by the same "Cotrol Plane". Each node in a cluster has a "kublet": software that handles communication with the Control Plane. All work within a cluster is handled on these virtual machines, and as needs change nodes can be added, removed, and adjusted as necessary.

## Kubernetes: Objects
Objects are persistent resources that are managed by Kubernetes: they are used to manage the state of the cluster. Typically YAML and/or JSON files are used to describe the objects you want, and these descriptions are taken by Kubernetes to determine the "desired state" of the cluster. Kubernetes will compare its "current state" against the "desired state" of the cluster, and if they don't match (objects are missing, mislabeled, etc) then Kubernetes will take action to change its current state so that it matches the desired state.

## Kubernetes: Declarative vs Imperative
One of the key benefits of using Kubernetes is its self-sufficiency: All you need to do is tell it what you want and then it will do the heavy lifting of creating resources, spinning up containers, and exposing your containers to end users according to your specifications. Kubernetes was designed to be used "declaratively" in this manner: you create YAML/JSON files that indicate your desired state, Kubernetes takes that info and runs with it.

However, no system is perfect, and no deployment stays static forever: companies grow and need to accomodate more users, mistakes make their way into production environments, the list goes on. Kubernetes supports "imperative" interactions with the cluster, commands that have a one-one relationship with the objects in the cluster. Objects can be deleted, scaled up, recreated, and more.

Both declarative and imperative interactions are perfectly fine, but the declarative method of interacting with Kubernetes (creating YAML/JSON files and having K8s use them to set the desired state of the cluster) is the prefered way of interacting with your clusters.

## Kubernetes: Object Types

### Pods [docs](https://kubernetes.io/docs/concepts/workloads/pods/)
pods are atomic units of deployment within Kubernetes: containers will ALWAYS run in a pod, and multiple containers may exist within the same pod. This makes the pod itself a wrapper to containers. It provides a way of sharing an execution environment, all containers will their pods' IP address, ports, file system, etc. This can be useful if, say, you are hosting your own database and want to connect your small web app to this local database.

That being said, "loose coupling" is considered to be best practice. This is where a pod has a single container and resources are shared only when absolutely necessary. This is particularly helpful because a Pod is only considered "ready" when all its containers are running successfully.

### Volumes [docs](https://kubernetes.io/docs/concepts/storage/volumes/)
Volumes are storage options that can be attached to a pod, and there are two kinds: ephemeral and persistent. An ephemeral volume has the same lifespan of the pod it is attached to: when the pod is destroyed the content of the volume is also deleted. Persistent volumes, on the other hand, have a lifespan that goes beyond the pod. These typically are services like AWS EBS, Azure file, etc. When data is persisted with these services the content is accessible to all pods that are linked to them, no matter how many times the pods are destroyed and recreated.

### Services [docs](https://kubernetes.io/docs/concepts/services-networking/service/)
Services are objects that can be used to expose applications running in pods. There are a few spcific kinds of services that can be used to easily wrap/allow access to your apps:
- ClusterIP
- NodePort
- LoadBalancer

#### ClusterIP
A ClusterIP services allows you to wrap one or more pods behind a single INTERNAL access point. This means that all the pods associated with the service are accessible behind a single IP address that K8 sets up. However, this access point is only available within the cluster (cross pod communication can happen): this type of service does not allow external access to the wrapped pods.

#### NodePort
A NodePort is similar to a ClusterIP in that it wraps one or more pods behind a single IP address, but a NodePort will expose the access point to external traffic. While this is an option for creating access to your application, it is not recommended for a production environment, due to the limited ability to perform any sort of external load balancing. It is, however, a valid choice for a development environment.

#### LoadBalancer
A LoadBalancer is the prefered choice for a production environment: it determines where external requests to your cluster are sent, and is typically used with an Ingress object to facilitate which service will handle an external request to your application. However, load balancers are usually expensive, which can be a drawback for a newcomer to Kubernetes.