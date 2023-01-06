# Useful Commands to Know

## kubectl
Anytime you want to interact with your clusters you have to start with the "Kube Control" command
```cli
kubectl {action}
```

## get
the get command returns information about the object type indicated (pods, services, etc)
```cli
kubectl get {object type}

kubectl get all # returns information on all K8s managed objects
```

## apply
the apply command is the preffered way of updating the desired state of the cluster: the -f flag is used to tell kubernetes what directory/manifest files to look through to update configurations
```cli
kubectl apply -f {directory}

kubectl apply -f {path to manifest}

kubectl apply -f {path to manifest one} -f {manifest two}
```

## describe
the describe command returns detailed information about the object targeted by name. You must include what type of object it is
```cli
kubectl describe {object type} {object name}
```

## logs
the logs command returns the logs sent to the stdout of the pod indicated. You can also stream the container stdout logs with this command
```cli
kubectl logs {pod}
kubectl logs {pod name} -c {container name} # returns the logs of the container
```

## delete
the delete command will tell Kubernetes to stop managing the object you indicate. Note this can have a cascading effect: deleting a deployment will also delete the managed replicaset and pods that the deployment was managing
```cli
kubectl delete {object type} {object name}
```

## rollout
the rollout command provides options for updating resources in the cluster
```cli
kubectl rollout history deployment {deployment name} # provides details on revisions to the deployment

kubectl rollout undo deployment {deployment name} # reverts a deployment to the previous revision
```

## scale
the scale command lets you change the number of replicas in a deployment
```cli
kubectl scale --replicas={new number} {object type} {object name} 
``` 