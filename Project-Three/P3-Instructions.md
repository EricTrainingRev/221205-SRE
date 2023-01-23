# P3 Overview
The goal of P3 is to give you all experience working through an incident (including assigning roles, communicating with a stakeholder, keep a live record of actions taken, and resolving the incident) and then writing up a comprehensive postmortem afterwards (note that due to a lack of resources and the repetitive  nature of this project, we will not be focusing on mitigating issues in this project). You will be assigned planetarium deployments, but your team will have freedom in how it deploys and updates monitoring tools.

There will be two main stages to the Project:
- stage 1: prep work (~1 week)
    - everyone will work on creating broken versions of the Planetarium application
        - everything from simple breaks (broken functions, wrong db credentials) to more complex issues (poorly optimized functions, invisible characters, intermittent  issues, bad docker files, etc) are desired
        - ideally everyone would create a range of broken apps that vary in degree of how easy the problem is to diagnose and how easy it is to fix the application
    - once a broken app is complete and the README markdown file is created you will zip the main directory and email it to your instructor (zipped directory should be ~100 kilobytes in size)
        - it would be helpful to include a startup script as well with the kubectl and helm commands needed to get the whole deployment up and running
- stage 2: Incident Management (~3 weeks)
    - your team will be assigned a version of the broken planetarium to deploy
        - you will be given the manifests, no source code yet
    - your team will begin monitoring the deployment via Grafana
        - one or more team members should set up Thunderclient or a similar tool to make consistent requests to the cluster
    - Once an issue is detected an Incident should be declared and Incident Management should start
        - Your team should rotate who starts as the Incident Commander, and you should also alternate who is assigned the other roles in order to make sure everyone gets experience in each position
        - Make sure you take into account all the available tools (metrics, logs, kubectl commands, etc) in order to diagnose the issue
        - Once you have determined the issue you will report your finding to the stakeholder (your trainer) who will release the source code to you
        - Once you have the source code you will fix whatever is causing the issue, create a new image, and deploy the new image
        - If your fixes solve the issue you will report your development to the stakeholder, if not, you will still give the stakeholder an update, and continue to work on the incident until it is resolved

# P3 Downtime
While waiting for an incident to occur there are some activities your team should work on to stay busy:
- create a deployment strategy (canary or blue/green) and implement it when you deploy a broken application
    - inform the stakeholder if you need to make any changes to the manifest files you are given
    - you can work on updating the planetarium in order to continue familiarizing yourself with Java source code 
- update your Grafana dashboard with more graphs to make monitoring easier
    - are there any metrics that could indicate your SLIs will be in the danger zone soon?
    - Can you update your SLIs to have higher levels of precision and recall?
- add more alerts
    - are there any scenarios that could indicate your SLIs are in danger?
    - Can you update your alerts to have lower reset and detection times?
- update the tools you use during incident management
    - Do you have an easy to access document for live notes?
    - Do you have a simple way of sharing code snippets?
    - Do you have a plan for effective ways to pair program?
- design more broken planetarium apps
    - be sure to indicate who all worked on any broken apps you submit

Remember that every incident you manage and postmortem you complete should give you an opportunity to improve how you do things: take the lessons you learn from each incident and improve your SRE setup as the project progresses.
