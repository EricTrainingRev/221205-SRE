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