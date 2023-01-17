# Alerting & Burn Rates
You all have some experience working with SLIs, SLOs, and alerts by this point. You have written PromQL queries that return values that you use to determine whether your SLOs are being met or not, and you know how to create alerts that trigger if the SLO is not being met. This has been rather primitive alerting though, we can become more refined in the way we think about alerting and in the way we talk about alerting

## Alerting Terminology
- Significant Event
    - something that consumes a large portion of your error budget
        - if your SLO dictates 99.9% of your http requests over a 30 day period should be handled successfully, and you have a period of time where 50% of all requests fail (think 10-30 minute period) this is significant
        - same scenario, but only one or two requests fail in a 10-30 minute range, this is not significant
    - Significant events are not meeting an SLO over a meaningful time-frame (or multiple time-frames)
- Precision
    - proportion of events detected that were significant
        - aka do your alerts trigger when unnecessary, or do they alert on significant events?
        - 100% precision means every alert related to a significant event
            - 100% precision does not mean that every significant event triggered an alert, just that every caught significant event did trigger an alert
- Recall
    - proportion of significant events detected
        - aka how good are our alerts at triggering on significant events?
        - 100% recall means every significant event triggered an alert
            - 100% recall does not mean that every alert was a significant event, just that they were caught
- Detection Time
    - how long does it take for an alert to trigger?
        - longer detection times can reduce the speed at which you respond to significant events
        - shorter detection times, on the flip side, can lead to responding to non-significant events when it is not necessary
- Reset Time
    - how long does it take for an alert to reset?
        - this can be dangerous because longer reset times can lead to missing new significant events
        - on the flip side, shorter reset times can lead to more alerts triggering, which can be frustrating/distract from real issues if your alerts are tied to things like a pager or email/chat notifications

## Pre-Burn Rate Alerting Options

### Alert When SLI >= SLO
This kind of alerting is when you have your alerts trigger anytime your SLI indicates you are not meeting your SLO. For instance, if you have an SLO that 99.9% of http requests should be handled successfully, and your metrics indicate your success rate is only 98%, then an alert triggers. This is the kind of metric tracking and alerting you all have been using so far. One way of measuring your metric would be to do something like this:
```
sum(rate(http_server_requests_seconds_count{job="sports", status!="500"}[5m])) 
/ 
sum(rate(http_server_requests_seconds_count{job="sports"}[5m]))
```
This is any easy way to check what your success rate is, however, there is another way you could handle this: you could flip this and try to check what the failure rate of request is
```
sum(rate(http_server_requests_seconds_count{job="sports", status=~"5.."}[5m])) 
/ 
sum(rate(http_server_requests_seconds_count{job="sports"}[5m]))
```
the formula above would return what the failure rate of requests made is, not what the success rate is( keep this idea of tracking failure rate in mind for later sections)

There are definite pros and cons to alerting off this simple system
- pros
    - really good detection time: you get almost immediate alerts when your SLIs are indicating that you are missing your SLOs (really this is dependent on your scraping times set for Prometheus)
    - really good recall: because you are directly comparing your SLI to your SLO anytime the metrics indicate a problem you will get an alert
- cons
    - there is potential for a significant number of alerts triggering for non-significant events
    - very low precision: you will get alerts off EVERY time your SLI indicates you are not meeting your SLO, so every non-significant event is going to trigger an alert

## Alerting When SLI >= SLO Over a Longer Range
By tracking your metrics over longer periods of time (5m, 30m, 1h, 1d, etc) you can reduce the negative effects of the simple SLI >= SLO alerting rules, but this creates some drawbacks of its own
- pros
    - this methodology still has good detection time, but it is a little slower than the previous method
    - you will have high precision with this system as well
- cons
    - this methodology can lead to poor reset times for your alerts
    - longer rate measurements can be more resource costly

## Adding Pending Period to Alerts
You can add a pending period to your alerts to help facilitate whether a significant event is happening or not
- pros
    - having a pending period can help to further increase your precision
- cons
    - poor potential recall
        - an issue could be short in nature but repetitive, causing your alert to constantly pend but never actually fire, thus you miss what is actually a significant event
    - poor detection time
        - see above issue

## Introducing Burn Rate
Burn Rate is a value that represents how quickly you are using up your error budget. Take for example the following SLO over a 30 day period: 99.9% of HTTP requests should be handled successfully. For a 99.9% success rate, a 0.1% failure rate would constitute a burn rate of 1. What does this value actually do for us? Well, a burn rate of 1 indicates that, after a 30 day time period of 0.1% failure rate, your Error Budget would be 100% used up. 

However, let's say the failure rate is 0.2%: this would constitute a burn rate of 2 instead of one. This means that your service that has a 0.2% failure rate will eat through your entire error budget in 15 days time. Here is a table showing how this could play out with more numbers
|Burn rate| Error Rate for HTTP handling| Time for Error Budget to be used up|
|---------|-----------------------------|------------------------------------|
| 1 | 0.1% | 30 days |
| 2 | 0.2% | 15 days |
| 10 | 1% | 3 days |
|1000| 100%| 43 minutes|

Working with a burn rate, we can now change the way we are alerting with our services: the alert rule we could create would look something like this:
```
sum(rate(http_count{job="sports", status=~"5.."}[5m])) 
/ 
sum(rate(http_count{job="sports"}[5m]))
>=
burn-rate * 0.001
``` 
This formula would create an alert that triggers anytime the failure rate of the service being measured is at or greater than the burn rate multiplied by 0.001 (which will allow us to check the percentage of failure  against the burn rate). This new system has some pros and cons to it
- pros
    - good precision
    - good detection time
- cons
    - low recall
    - can have poor reset times for longer ranges

## Multiple Burn Rate alerting
We can make the Burn Rate alerting system more robust by creating multiple alerts based off of longer and longer time rates being measured. So for instance we could alert off the following options:
|Error Budget consumption| Time Window | Burn Rate | Notification option |
|------------------------|-------------|-----------|---------------------|
| 5%                     |   6 hour    |    6      |      page           |
| 10%                    |  3 days     |   1       |        ticket       |

By manipulating the time frame you are working with, the budget being used up, and the actual burn rate value, you can determine what kind of notification should happen based up the error budget being consumed.
- pros
    - highly adaptable
    - high precision
    - high recall
- pros
    - this system can be difficult to mange/understand
        - difficult to update if your change your SLO tracking timeframe (30 days to 25 days)
        - easy to create overlapping alerts that will trigger for the same significant event, but could also trigger for different significant events, and you won't know which is the case until you dig deeper
    - this system typically has long reset times

## Multi window/Burn Rate Alerting Method
This strategy combines all the previous strategies we have looked at, takes the best parts of each of them, and uses them to try and provide the highest precision and recall percentages while keeping detection time and alert reset times down. In this methodology a short and long range are used with the same data to determine whether an alert should trigger or not:
```
sum(rate(http_server_requests_seconds_count{job="sports", status=~"5.."}[5m])) 
/ 
sum(rate(http_server_requests_seconds_count{job="sports"}[5m]))
>
14.4 * 0.001
and
sum(rate(http_server_requests_seconds_count{job="sports", status=~"5.."}[1h])) 
/ 
sum(rate(http_server_requests_seconds_count{job="sports"}[1h]))
>
14.4 * 0.001
```
Google recommends that your short range be 1/12th of the long range you use to create your alert. Google actually has a recommended starting point when working with Multi window/burn rate alerting (this table assumes 30 day period with 99.9% success):
|Severity|Long Window|Short Window|Burn Rate|Error Budget Consumed|
|--------|-----------|------------|---------|---------------------|
|page    |1 hour     | 5 min      | 14.4    |    2%               |
|page    |6 hours    | 30 min     | 6       |    5%               |
|Ticket  |3 days     | 6 hours    | 1       |    10%              |

- pros
    - this is one of the more flexible options when it comes to creating alert rules: it can easily be configured to track data over short and long periods of time
    - high precision 
    - high recall
- cons
    - this is an incredibly complex way of handling alerts, which ultimately can make it hard to manage/configure the alerts.

## Tips & Tricks
- Make use of recording rules
    - this makes adjustments to your rules easier over time, since you have a single location (your recording rule) that you can change and your alerts will be adjusted accordingly
    - recording rules are also more efficient for handling complex calculations
- Don't reinvent the wheel
    - Google already did the hard work figuring out what works and what does not work for a large scale enterpise operation: start with what they have done, and then adjust their work to fit your needs
- Make use of resources that handle the toil for you
    - Make use of tools like PromTools that can generate many of these alerting and recording rules for you, and any others you find in your time as an SRE