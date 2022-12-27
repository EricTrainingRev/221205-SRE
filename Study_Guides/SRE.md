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
3. Assuming action needs to be taken (SLI indicates SLO will not be met in the near future) SREs determine what action needs to be taken in order to maintain the SLO
4. SREs execute whatever action was deemed necessary in step 3 and then continue to monitor the system

## SLA
Service Level Agreements (SLAs) are legal agreements between end users and a service provider. SRE teams help lawyers and other decision makers understand what is and is not resonable when it comes to the state of the system and what the company should agree to. A good rule of thumb is to be conservative in your estimates when discussing SLAs, and make sure your SLOs are more stringent than any SLA agreed to

## Error Budget
An Error Budget is the "wiggle room" that a service is provided by its SLOs. For instance, in the example above, one of the requirements was that the service must successfully handle 99.9% if all requests. If we subtract this from 100% that leaves .1% room for error: this is our service's error budget. Having an Error budget helps the development team and the SRE team to balance releasing new features with maintaining the system as a whole. For instance, if the Error budget has not been used up, the development team can be agressive with their release schedule of new features: they have the wiggle room for mistakes to be made. On the flip side, if the Error budget has been used up the SRE team can remind the dev team that the wiggle room is gone, and so dev work can be shifted to other areas (such as debugging the issues causing the Error budget to be used up). 

## Toil
Toil can be defined as " the kind of work tied to running a production service that tends to be manual, repetitive, automatable, tactical, devoid of enduring value, and that scales linearly as a service grows." An example of this would be manually deploying an updated application to the production environment: an SRE team would make it an early goal to automate this process in order to reduce toil. Keep in mind that a team will never be able to completely eliminate toil, but SRE teams can work to minimize their toil levels.

# Tools

## [Promtail](https://grafana.com/docs/loki/latest/clients/promtail/)
Promtail is a tool that allows us to push log files generated by our applications to another location (we will be using Loki). When pushing content, Promtail will add labels to the logs that makes it easy to recognize where the logs are coming from, and allows tools like Loki to better organize and label the content

## [Loki](https://grafana.com/oss/loki/)
Loki is a log aggregation system that can store, label, and provide easy access to logs accross your entire infrastructure. Loki is unique in that it it works with and indexes labels instead of entire log lines, allowing for faster retrieval and organization. With its labeling system it is also easy to recognize where a log was created, and even allows for other systems to integrate and create alerts based off custom logs you create

## [Grafana](https://grafana.com/grafana/)
Grafana is a visualization tool that integrates well with Loki (they were made by the same company). Grafana primarily works by creating "dashboards" of information that allow you to visulize whatever data you are looking at. It also has querying tools that allow you to find specific logs stored in your Loki instance, and it even includes powerful query building tools that let you procedurally craft queries to send to your Loki instance in order to find the correct log files you are looking for