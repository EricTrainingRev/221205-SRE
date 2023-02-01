# Backlog

## Epics
- Prometheus should be configured to trigger alerts designed to warn that SLOs are in danger due to cluster conditions
    - memory usage high
    - cpu usage high
    - file space low
    - pods are consistently resetting
    - etc
- Prometheus should be configured to alert using a multi-window multi-burn rate strategy for relevant SLIs
- Alertmanager should be integrated into the monitoring deployment in order to better facilitate alerts
    - can silence alerts
    - can integrate third party resources to receive alert information
        - email
        - pagers
        - communication app (teams channel)
        - etc
- Planetarium Cluster should be configured so monitoring tools and planetarium pods are hosted on separate nodes, ensuring that any cascading failures on the planetarium nodes do not hinder the monitoring tools, and vice versa
    - *researching taints and tolerations would be a good starting point for this epic*
- Sensitive information needed for Planetarium and monitoring tools to work should be stored and referenced as secrets in the cluster
- data saved by Prometheus and Loki should be stored outside of the cluster in a persistent volume
- Jenkins should be deployed so a DevOps pipeline can be established and utilized
- Kubernetes resources should be managed by Terraform configurations to allow for more robust Configuration as Code implementation

## Helpful Links
- [Prometheus Operator help](https://github.com/prometheus-operator/prometheus-operator/tree/main/Documentation)
- [Trello Scrum Board](https://trello.com/b/dFzygb01/scrum-board)
    - you will need to make free trello accounts to use this
- [Github projects](https://docs.github.com/en/issues/planning-and-tracking-with-projects)
    - another way to keep track of your Scrum work