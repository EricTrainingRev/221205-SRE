# Helpful Resources
This is a collection of various web resources that can enhance your understanding of the tools/resources you have learned about during your training at Revature

## SRE
- [Google's SRE Handbook](https://sre.google/sre-book/table-of-contents/)
    - HIGHLY recommend you peruse this resource
- [Grafana Documentation](https://grafana.com/docs/grafana/latest/)
    - includes user guides, tutorials, and lots of other useful resources
    - [this article](https://grafana.com/blog/2023/01/25/monitoring-kubernetes-layers-key-metrics-to-know/?utm_source=grafana_news&utm_medium=rss) has information about what kind of metrics kubernetes exposes and you can graph for easy viewing
- [Loki Documentation](https://grafana.com/docs/loki/latest/)
    - [Promtail Documentation](https://grafana.com/docs/loki/latest/clients/promtail/) is also located here
- [Prometheus Documentation](https://prometheus.io/docs/introduction/overview/)

## AWS
- [AWS CLI documentation](https://docs.aws.amazon.com/cli/latest/userguide/cli-chap-welcome.html)
- [AWS EKS documentation](https://docs.aws.amazon.com/eks/latest/userguide/what-is-eks.html)
    - this includes helpful guides and commands for working with AWS EKS
    - [Instructions for scaling the nodes in a cluster](https://eksctl.io/usage/managing-nodegroups/#scaling-a-single-nodegroup) NOTE: this requires you to have the aws cli and eksctl programs installed, along with proper IAM permissions

## Kubernetes
- [Kubernetes Documentation](https://kubernetes.io/docs/home/)
    - [This is a link](https://kubernetes.io/docs/reference/kubectl/cheatsheet/) to commonly used kubectl commands
- [Drain: a graceful way to remove pods from a node](https://kubernetes.io/docs/tasks/administer-cluster/safely-drain-node/)
    - particularly useful if you need to shut down a node for whatever reason
- [Taints and Tolerations: a way to organize your pods](https://kubernetes.io/docs/concepts/scheduling-eviction/taint-and-toleration/)
- [Secret Objects: benefits and limitations](https://kubernetes.io/docs/concepts/configuration/secret/)
- [Managing Resources: tips, tricks, and best practices](https://kubernetes.io/docs/concepts/cluster-administration/manage-deployment/)

## Terraform
- [Terraform Documentation](https://developer.hashicorp.com/terraform/docs)
    - Includes a tutorial library with detailed examples for common Terraform deployments
    - [This EKS tutorial](https://developer.hashicorp.com/terraform/tutorials/kubernetes/eks) includes the necessary base configurations to create a simple cluster (NOTE: EKS is not a free tier eligible resource) 
- [Mange K8s resources with Terraform](https://developer.hashicorp.com/terraform/tutorials/kubernetes/kubernetes-provider)
    - you should manage the cluster itself and the cluster resources seperately

## Unix
- [Ryan's Tutorials](https://ryanstutorials.net/) is a collection of informative tutorials that includes walkthroughs of working in a linux environment and creating bash scripts