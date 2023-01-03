# Site Reliability Engineer Study Guide

## What is an SRE?
Coined by Google, a Site Reliability Engineer (SRE) is responsible for approaching IT needs from an engineering perspective. SREs are invovled in the following activities:
- application deployment
- application configuration
- application monitoring
- metrics tracking

These activities used to be handled manually by ops teams, but SREs with their software engineering knowledge are able to automate these processes in order to free up time to handle more novel problems or to eliminate toil. Another key activity SREs work on is monitoring Service Level Indicators (SLIs) in order to ensure that Service Level Objectives (SLOs) are being maintained

## SLI & SLO
One of the jobs of an SRE is to monitor deployments, but how do the SREs know what needs to be monitored? This is determined in part by the Service Level Objects set by the organization they work for. A SLO would read like the following: "99.9% of all http requests to our application should complete successfully within 100 miliseconds". For this hypothetical application we are discussing, the service goal we have is that 99.9% of all user interactions with our application will be handled correctly, and that they will be handled within 100 miliseconds of time. All SLOs are set over a period of time, such as 2 weeks, 1 month, etc.

In order to validate whether we are achieving our SLO we would set up Service Level Indicators that track the performance of our application. For the SLO above, we would specifically want to track the % of request to our server that return non-500 status codes (400 status codes are acceptable because those indicate user error, something we can't control). For the latency we would want to track how long it takes for our application to handle requests and return a response (tracking the internal time specifically, there are things on the client side we can't control). 

Depending on the type of service provided and the intended end user, there are typically a few key SLIs to track (this list is not exhaustive):
- User Facing Services
    - availability (successfuly handled interactions)
    - latency (how long does it take to handle interactions)
    - throughput (how many interactions can be handled simultaniously)
- Storage Systems
    - availability
    - latency
    - durability (will my data still be there if there is a power outage?)
- Big Data
    - throughput
    - latency

There are a few things to keep in mind when determining your SLOs and SLIs:
- limit your overall amount of SLOs
- make your SLOs as simple as is resonable
- perfection is a nice ideal, impossible in practice

## SLI & SLO in action
1. SREs track system SLIs
2. SREs compare SLIs to SLOs to determine if action needs to be taken
3. Assuming action needs to be taken (SLI indicates SLO will not be met in the near future, or that the SLO is not currently being met) SREs determine what action needs to be taken in order to maintain the SLO
4. SREs execute whatever action was deemed necessary in step 3 and then continue to monitor the system

## SLA
Service Level Agreements (SLAs) are legal agreements between end users and a service provider. SRE teams help lawyers and other decision makers understand what is and is not resonable when it comes to the state of the system and what the company should agree to. A good rule of thumb is to be conservative in your estimates when discussing SLAs, and make sure your SLOs are more stringent than any SLA agreed to

## Error Budget
An Error Budget is the "wiggle room" that a service is provided by its SLOs. For example, if a service requires 
99.9% availability, you can calculate the SLO's Error Budget by subtracting it from 100%. That leaves .1% room for error: this is our service's error budget. Having an Error budget helps the development team and the SRE team to balance releasing new features with maintaining the system as a whole. For instance, if the Error budget has not been used up, the development team can be agressive with their release schedule of new features: they have the wiggle room for mistakes to be made. On the flip side, if the Error budget has been used up the SRE team can remind the dev team that the wiggle room is gone, and so dev work can be shifted to other areas (such as debugging the issues causing the Error budget to be used up). 

## Toil
Toil can be defined as " the kind of work tied to running a production service that tends to be manual, repetitive, automatable, tactical, devoid of enduring value, and that scales linearly as a service grows." An example of this would be manually deploying an updated application to the production environment: an SRE team would make it an early goal to automate this process in order to reduce toil. Keep in mind that a team will never be able to completely eliminate toil, but SRE teams can work to minimize their toil levels.

# Tools

## [Promtail](https://grafana.com/docs/loki/latest/clients/promtail/)
Promtail is a tool that allows us to push log files generated by our applications to another location (we will be using Loki). When pushing content, Promtail will add labels to the logs that makes it easy to recognize where the logs are coming from, and allows tools like Loki to better organize and label the content.

Once Promtail has discovered the logs it begins to "tail" them(constantly reading and pushing new discoveries). This allows you to keep up to date records in whatever storage option you choose. Promtail is able to read both regular log files and various forms of compressed files(.gz, .z, .bz2, .tar.gz). Noteably, Promtail does not currently support reading .zip extensions, but the developers do want to add support for this in the future
```yml
# Basic Promtail configuration file

# the server section tells Promtail what port to listen on: Promtail can handle a few HTTP requests
server:
  http_listen_port: 9080
  grpc_listen_port: 0

# this allows us to create a file that tells Promtail where it left off when tailing logs
positions:
  filename: /tmp/positions.yaml

# this tells Promtail where to push the tailed logs
clients:
  - url: http://lokiUrl:3100/loki/api/v1/push # this tells promtail to send the logs to Loki

# this is where you can provide details and labels for different tailing jobs
scrape_configs: # this section tells promtail where to find the log files
  - job_name: system
    static_configs: # we are telling Promtail where to find the logs
      - targets:
          - localhost # here we tell it to target its own container
        labels:
          job: sports # this adds a "job" label with a value of "sports" to the logs for identification
          __path__: /var/log/staticLogs.log # this is the path to the logs that need to be tailed
```
```yml
# docker-compose basic setup
version: '3'

services:

  promtail:
    image: grafana/promtail:latest # may want to choose a specific version of the image
    container_name: promtail-container
    volumes:
      - ./configs/promtail-config.yml:/etc/promtail/promtail-config.yml # configuration outside of container:config saved in container
      - ./logs/staticLogs.log:/var/log/staticLogs.log # log we want to save:log in promtail

    # the command section below adds the given flag to the entrypoint command specified in the promtail image: in this case it tells Promtail
    # to use the configurations provided in the config file
    command: "--config.file=/etc/promtail/promtail-config.yml"
```

## [Loki](https://grafana.com/oss/loki/)
Loki is a log aggregation system that can store, label, and provide easy access to logs accross your entire infrastructure. Loki is unique in that it it works with and indexes labels instead of entire log lines, allowing for faster retrieval and organization. With its labeling system it is also easy to recognize where a log was created, and even allows for other systems to integrate and create alerts based off custom logs you create
```yml
version: '3'

services:

  loki:
    image: grafana/loki:latest
    container_name: logging-loki
    ports:
      - 3100:3100
```

## [Prometheus](https://prometheus.io/docs/introduction/overview/)
Prometheus is an open sourced monitoring tool that supports alerting and custom metrics. It stores the metric information it "scrapes" from applications in a time series database (it time stamps the data and organizes it chronologically) and supports performing various calculations on the data using PromQL. PromQL is a language that allows for grabbing data from a Prometheus instance and manipulating it in various ways (such as determining the sum of multiple metrics or calculating the value of a counter metric over a rate of time). Prometheus works with three key metric types: Counters, Gauges, and Histograms
- Counters
    - counter values are those that only go up, or if the app providing the metric restarts the counter resets to 0 before increasing again
    - this metric is used to track things like http requests served, errors, visitors to a webpage, etc.
- Gauges
    - gauges measure values that fluctuate by nature: you can't go from 5 http requests made to your application to 4, but you can go from 3 users logged in to 0, then back up to 2, then down to 1, etc.
- Histograms
    - histograms are "configurable buckets" that hold multiple values. These types of metrics typically end with _sum or _count because they contain the sum of all measurements or the total number of measurements made over time respectively

All this information is acquired by Prometheus scraping an application: this is a process of pulling data from a source (typically via an http request) and then storing the data in the time series database.
### Counters and Rate
A common practice when working with counter metrics in Prometheus is to pass them through a [rate](https://prometheus.io/docs/prometheus/latest/querying/functions/#rate) function. This is a particularly useful thing to do when working with percentages (think calculating HTTP success rates): if you were to only measure the overall success rate of an application then ANY failed requests would permanently mare the metric. A more meaningful way to measure the success rate would be to measure the metric over time, hence the rate function. So, for calculating the success rate of http request to your application over time, you would use a formula like this (the [sum](https://prometheus.io/docs/prometheus/latest/querying/operators/#aggregation-operators) value is required to ensure all http requests are accounted for when making the calculation):
```promql
sum(rate(httpSuccessCount{job="jobLabel"}[5m])) / sum(rate(httpTotalCount{job="jobLabel"}[5m]))
```
the promQL query above will calculate the average success rate of http requests for the application over a five minute range. If you changed the [5m] portion to 10m, 30m, or 1h, you would change the rate to 10 minutes, 30 minutes, or an hour respectively. By doing this you will better be able to track anomalous events and discover meaningful trends in your metrics

a few useful formuals:
```cli
# http success rate
sum(rate(successCount[time])) / sum(rate(totalCount[time]))

# latency
sum(rate(timeTotal[time])) / sum(rate(totalCount[time]))

# throughput
sum(rate(totalCount[time]))
```

### Alerts
Prometheus also includes the capability of creating alerts that trigger off specified conditions. For instance, you may want an alert to trigger if your application is down (this is something you need to know, after all), or you may want an alert to trigger if your application is failing to meet an SLO as determined by your SLIs, etc. You can use one or more promQL queries and make a check against them to create a trigger for your alert: if the trigger condition is met then your alert fires. Alert triggers can be set up to trigger secondary effects, such as paging an on-call SRE, sending an email, or pinging a slack channel, but for now we will just view active alerts via Grafana. You can create alerts directly in Grafana, but we will be using a yml configuration file to tell Prometheus about our alerts. These alerts can be set to trigger immediately or to "pend" for a set amount of time.
```yml
# we can organize our alerts into different groups
groups:
- name: example
  rules:

# this is a simple alert that "pends" for 5 minutes before firing. If the application starts up again before the 5 minutes are up the alert resets
  - alert: AppDown # this is the name of the alert
    expr: up == 0 # this is the promQL query used to trigger the alert
    for: 5m # this tells prometheus how long the alert should "pend"
    labels: # we can assign various labels to our alerts
      severity: warn
    annotations: 
      summary: "App is down" # this allows us to view a brief explanation of the alert
      description: "Prometheus has detected the application is not running for over 5 minutes" # this is a more detailed explanation

  - alert: APIHighLatency:5min # alert name
    expr: sum(rate(someSum{job="app"}[5m])) / sum(rate(someCount{job="app"}[5m])) > 0.2 # triggers if latency greater than 200 miliseconds
    labels:
      severity: warn
    annotations:
      summary: "5min latency too high"
      description: "application is taking too long to handle requests in a 5 minute range"
```
the alert rules would go into their own configuration file, and then the docker compose setup and prometheus configuration file would look something like this:
```yml
# configuration file
global:
  scrape_interval: 15s # this tells Prometheus to scrap every 15 seconds

rule_files:
  - prometheus-rules.yml # this is where the alert rules are located

scrape_configs:
  - job_name: prometheus # Prometheus actually exposes its own metrics that can be tracked
    static_configs:
      - targets: 
        - prometheus:9090 # this tells Prometheus to target itself
  - job_name: app # this adds the "app" label to all metrics provided by this scrape config
    metrics_path: /actuator/prometheus # this endpoint is added by micrometer dependency in Spring app using Actuator
    static_configs:
      - targets:
        - app:8080 # this tells prometheus to target port 8080 of a docker service called app, since we are using docker compose
```

```yml
# docker compose
version: '3'

services:

  prometheus:
    image: prom/prometheus:latest # think of this as your database for your metrics over time
    ports:
      - 9090:9090
    volumes:
      # you need to get the prometheus configurations and rules into the container
      - ./configs/prometheus-config.yml:/etc/prometheus/prometheus-config.yml
      - ./configs/prometheus-rules.yml:/etc/prometheus/prometheus-rules.yml
    container_name: prometheus-container
    command: "--config.file=/etc/prometheus/prometheus-config.yml" # we need to tell Prometheus what file has its configurations
```
## [Grafana](https://grafana.com/grafana/)
Grafana is a visualization tool that integrates well with Loki (they were made by the same company) and Prometheus. Grafana primarily works by creating "dashboards" of information that allow you to visulize whatever data you are looking at. It also has querying tools that allow you to find specific logs stored in your Loki instance, and it even includes powerful query building tools that let you procedurally craft queries to send to your Loki instance in order to find the correct log files you are looking for. It also integrates well with Prometheus: the dashboard capabilities in particular allow you to create custom graphs and views that let you see the most pertienent information you need displayed in graph or table formatting without having to manually send the queries every time you want the data. Grafana also allows you to view Prometheus alerts and to display them in a dashboard alongside your metrics, giving you the most relevant data you need to know at a moments glance. There are a few Grafana sectors to be aware of:
- Dashboards
    - this is where you can create or view your own custom dashboards to display relevant metrics and alerts
    - there are many publically available templates you can work off of as well
        - [free dashboards](https://grafana.com/grafana/dashboards/)
- Explore
    - this tool allows you to interact with data sources Grafana has integrated with (like Prometheus and Loki)
- Alerting
    - this tool lets you create or review alerts tied to integrated data sources (like Prometheus)
- Configurations/Data Sources
    - this is where you can configure Grafana to connect with your various data sources

```yml
# Basic docker compose configuration
version: '3'

services:
  grafana:
    image: grafana/grafana:latest # default username/password:admin/admin
    container_name: logging-grafana
    ports:
      - 3000:3000
```

# Incident Management
At some point in your SRE career SOMETHING is going to go wrong: a deployment is going to fail, a service is going to stop responding to requests, a data center is going to lose power, etc. Your observation tools can inform you of the issue, or users can report an issue, or another SRE might notice the problem, there are many ways that issues can be reported. When these situations arise you have an "Incident" on your hands.

An Incident is some urgent problem that is (typically) out of the ordinary, and almost always requires multiple individuals to work on it to resolve the issue/s at hand. The ways in which incidents can occure are near infinite, so addressing each and every possibility is impossible. You can, however, have a discrete set of guiding principles that determine how you approach incidents when they arise. There are four principles suggested by Google that can help guide a team working to solve an incident:
1. Maintain a clear line of command
2. Designate clearly defined roles
3. Keep records of your work (debugging and mitigation actions)
4. Declare incidents sooner rather than later

Adding on to this, Google, in developing its SRE practices, draws inspiration from the Incident Command System designed by firefighters in 1968. In their system, there are three "C"s that help maintain focus and efficiency which Google uses to suppliment their Incident Mangement guiding principles:
- Coordinate
- Communicate
- Control

As stated in the Google SRE handbook: "When something goes wrong with incident response, the culprit is likely in one of these areas. Mastering the 3Cs is essential for effective incident response."

### Incident Management: Leadership Roles
Drawing from Google's practices again, they have three leadership positions during an incident to maintain coordination, communication, and control:
- Incident Commander
  - this individual handles communication between the different teams involved in an incident response, allowing those teams to focus on their work
  - the Incident Commander is also a delegator: as the incident develops and new work needs to be done it is the commander who should delegate the new work to new or current teams that are working on the incident
  - typically, the SRE who declares the incident starts as the Incident Commander, and then can hand off the role to someone else if necessary
  - The Incident Commander also handles the other leadership roles until the positions are assigned
- Communications Lead
  - this individual handles communications between the Incident Management team and those "outside" of the team
    - stakeholders
    - upper management
    - hr
    - etc
  - if the incident requires it, the Communications Lead also handles answering any inquiries about the incident from outside entities
- Ops Lead
  - this individual is in charge of the individuals/teams who are working to mitigate the incident
    - can redirect traffic from a corrupt VM to others
    - can rollback a deployment
    - can increase computing power in a VM
    - etc

### Incident Management: Postmortem
Eventually an Incident will be handled, the problem solved, and operations will return to normal. This is the time when a Postmortem document should be created by those involved with the incident. A Postmortem is an extensive report on the incident that should contain the following data (and more if necessary)
- When did the incident happen?
- Who was involved in solving the incident?
- What was the incident?
- What damage was done by the incident?
- What solved the incident?
- What actions were taken to mitigate the incident?
- When did things return to normal?
  - did things return to normal?

### Postmortem: When to write one?
Not every incident is going to require a Postmortem write-up: declared incidents that turn out to be small, easily solved issues don't require the extensive work. Postmortems are more useful when events like the following occur (again, not an exhaustive list):
- Data was lost
- User information was exposed
- Manual intervention was necessary
- The incident took an inordinate amount of time to solve and fix
- Monitoring tools failed to indicate the incident was in progress
- More than one team was needed to solve the incident

### Postmortem: Blameless Culture
Postmortems should be as exhaustive as is reasonable: the goal of these documents is to provide a learning experience and intructions for future teams that must deal with similar (or the same) incident. Consequently, this report requires all involved to be honest and open about their work, which can be daunting if you are an individual who helped create the problem in the first place. To combat the inclination to withhold information a "Blameless" culture should be developed surrounding Postmortem documentation. A "Blameless" Postmortem doesn't ignore the events and actions that led to the incident (or exaserbated it), but it does divorce the events/actions from the individuals who participated in them. The ultimate goal of the Postmortem is for those involved and studying the incident to learn and grow so that they can imporve in their work and the company can better handle future incidents. Blaming individuals in Postmortems is directly contradictory to the goal of continual improvement, and will ultimately encourage people to withhold information that might paint them in a bad light. When building Postmortem reports:
- focus on events, not individuals
- observations about individual or team actions should be constructive in nature, not degrading
  - if a team or individual messed up it should be noted, but comments about the messup should fundamentally be constructive and educational in nature: Postmortems are not for shamming people or teams
- anyone who reads the Postmortem should walk away with a new understanding of the system involved, what went wrong, and how to handle the same issue in the future

In order for a Postmortem to be most effective it must be a collaborative effort: a typical incident will involve multiple teams and individuals, and none of them will have the full picture of the incident. Therefore, the teams/individuals involved need to work together in order to create a complete report on the incident. For instance, the SRE team may have been focused on monitoring application metrics during the incident in order to make sure that other systems didn't fail during the event. While they were monitoring, the dev team was working to fix an issue with the newly created docker image that led to a vm crashing. The SRE team doesn't know what work the Dev team did, and the Dev team doesn't know how their work affected the overall performance and metrics of other systems. Together, they are able to create a complete picture of what happened and record it in the Postmortem.

Lastly, it is good practice to have senior memebers of the teams involved with the incident review the Postmortem to confirm that the details it provides are relevant. A Postmortem that just says a problem occurs and was solved is not useful for preventing/managing such incidents in the future. A Postmortem that delves into the issue, debugging strategies that worked and didn't work, and how the issue was solved provides lasting value for all teams invovled, and even those that weren't, since they can still benefit from the experience the incident management team gained from the incident.

