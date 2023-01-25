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
- [Kubernetes Documentation](https://kubernetes.io/docs/home/)
    - [This is a helpful link](https://kubernetes.io/docs/reference/kubectl/cheatsheet/) to commonly used kubectl commands
- [AWS CLI documentation](https://docs.aws.amazon.com/cli/latest/userguide/cli-chap-welcome.html)
- [AWS EKS documentation](https://docs.aws.amazon.com/eks/latest/userguide/what-is-eks.html)
    - this includes helpful guides and commands for working with AWS EKS
    - [Instructions for scaling the nodes in a cluster](https://eksctl.io/usage/managing-nodegroups/#scaling-a-single-nodegroup) NOTE: this requires you to have the aws cli and eksctl programs installed, along with proper IAM permissions
- [Terraform Documentation](https://developer.hashicorp.com/terraform/docs)
    - Includes a tutorial library with detailed examples for common Terraform deployments
    - [This EKS tutorial](https://developer.hashicorp.com/terraform/tutorials/kubernetes/eks) includes the necessary base configurations to create a simple cluster (NOTE: EKS is not a free tier eligible resource) 