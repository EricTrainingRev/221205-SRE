# Incident Management
- The goal of an SRE is to MANAGE incidents as they happen
- An incident is a serious occurance as defined by either your company, stakeholders, or users
    - company incident: can't access internal tools used to do job
    - stakeholders: a service is down that will hurt company relationships with the public
    - users: unable to access service, unexpected results, etc

There are some general guidelines when determining if an issue constitutes an Incident or not:
- Is the problem user facing?
- Are multiple teams/departments needed to solve the issue?
- Is it taking too long to solve the issue?
- Is a manual repsonse required?
- Did monitoring tools/alerts not alert you to the issue?
- Was any data lost?
- Was user data exposed?

## Incident Management Guiding Properties
- maintain clear line of command
- designate clearly defined roles
- keep records of debugging/mitigation solutions
- declare incidents sooner rather than later

## Incident Command System
Firefighters developed the Incident Command System in 1968 to better manage dealing with forest fires. There are three guiding principles for ICS that work well with the Incident management guiding principles
- coordinate
- communicate
- control

When managing an incident there are three primary leadership roles that should be filled by individuals who can consistently maintain an environment of control, communication, and coordination:
- Incident Commander
    - if you declare an incident, you start as the incident commander
        - you can hand off this responsibility later if necessary, but you will start in this role
    - you are the coordinator between teams
    - you delegate responsibilities
        - When an incident starts, you assume the Communications lead and Ops lead positions until you can delegate them
- Communications Lead
    - you handle communication with those outside of the incident management team
    - any pertintent information is given to the Incident Commander who can then share it with the appropriate teams
- Ops Lead
    - you handle the team that is working to solve the issue
        - this can be someone from SRE, or Dev, or another team. Once the source of the issue is found you might want to switch who is the Ops lead depending on the issue
    - they can roll back a deployment
    - they can redirect traffic from a corrupt node to another one
    - they can raise computing power for a virtual machine
    - etc.

# Postmortem
A Postmortem is a document that provides context for an incident after the fact. It should include as much detail about the incident as can be provided reasonably:
- when did the incident happen?
- who worked on the incident to solve it?
- what was the incident (basic summary of incident)
- what happened (more detailed explanation)
- timeline of events
- mitigation efforts taken
- damage done
- solution to incident
- recovery efforts
- etc

## Postmortem: Blameless culture
It can be very easy to think that your mistakes are yours alone and that you will be blamed/get in trouble for making a mistake: this kind of thinking can lead to incomplete Postmortem reports that don't actually help anyone deal with the same issue in the future. To avoid this, a Blameless culture should be encouraged surrounding Postmortem reports.
- Blameless incident summary
    - at 12:32 PM on Decemeber 25th 2022 a corrupt image was deployed to the production environemnt instead of the newly corrected one. Due to this, a memory leak spiraled out of control which led to vms crashing, rebooting, and the process repeating. After 2 hours of searching the logs and system metrics the typo was discovered in the deployment configuration, changed, and the system began to work as expected
- Blameful incident summary
    - Eric was at it again this weekend: he made a typo in the deployment configuration which caused the entire SRE and development team to miss out on Christmas with their families. After the other SREs and Dev team memebers (not Eric, he was crying in the corner) exhausted the logs and system metrics, Sally, the heor of the day (unlike Eric, remember, he made us all miss Christmas) realized that Eric made a typo in the deployment configuration, and then we forced him to fix it while we all stared at him.

There should be collaboration while creating the document, because not everyone has the whole picture. Also, senior memebers of the teams involved should review the document before it is approvied in order to ensure the document is sufficient.