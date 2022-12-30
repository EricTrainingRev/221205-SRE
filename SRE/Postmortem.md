# Postmortem Name (incident #)

## Summary Data
Date
- 2022-12-29

Authors
- Ted, Bill, Sally

Status 
- Complete, action items in progress

Summary 
- Shakespeare Search down for 66 minutes during period of very high interest in Shakespeare due to discovery of a new sonnet.

Impact
- Estimated 1.21B queries lost, no revenue impact.

Root Causes 
- Cascading failure due to combination of exceptionally high load and a resource leak when searches failed due to terms not being in the Shakespeare corpus. The newly discovered sonnet used a word that had never before appeared in one of Shakespeare’s works, which happened to be the term users searched for. Under normal circumstances, the rate of task failures due to resource leaks is low enough to be unnoticed.

Trigger
- Latent bug triggered by sudden increase in traffic.

Resolution
- Directed traffic to sacrificial cluster and added 10x capacity to mitigate cascading failure. Updated index deployed, resolving interaction with latent bug. Maintaining extra capacity until surge in public interest in new sonnet passes. Resource leak identified and fix deployed.

Detection
- Sally detected high level of HTTP 500s and paged on-call.

## Action Items

|Action Item|Type|Owner|Bug|
|-----------|-----|----|---|
|etc|etc|etc|etc


## Lessons Learned

- What went well
- What went wrong
- Where we got lucky


## Timeline
2022-12-29 (all times EST)
- 12:10 event 1
- 12:16 event 2
- 12:22 event 3
- etc

