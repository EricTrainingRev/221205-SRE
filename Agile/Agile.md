# Agile

## The old ways: Waterfall
Anytime a project (any project) gets started, there is a key question that needs to be asked: can I got back? This might seem like a silly question, but there are some fields/projects where the answer to this question is actually no: a bridge builder, if a mistake is made with the bridge, can't just click "ctr + z" to undo the mistake. If a brain surgon accidentally cuts the wrong part of the brain, he can't just glue it back on witout consequence. There are times when work needs to be done and it needs to be done right the first time because there is little to no room for error. This is not a bad thing in itself, but it does limit how you can approach problems when this "waterfall" mindset is used. This is the methodology that used to be prevelant in the software world.

Pros to the Waterfall methodology:
- it is very easy to know what the end goal is
    - usually the end goal is explicitly spelled out in some sort of contract
- it is very easy to know what the "next" step is
- helps to reduce confusion between teams
    - each team will usually have a specific role in the development process, no need to second guess yourself and the part of the process you are working in
- you don't need any in-depth knowledge of any sort of "ceremonies" or mindsets to do your job well in a Waterfall environment: once step A is finished, you move on to step B, then C, etc.

Drawbacks to the Waterfall methodology:
- if a client agrees to contract/terms, but later realizes that they don't actually want those features they agreed to, they are stuck
    - on the flip side, the developers can't go back and revise things with the client in order to better produce what the client wants
- the lack of "backwards" movement creates a lot of pressure to "get it right" the first time. This can be problematic if teams are all doing isolated jobs and don't necessarily know how the pieces work together
- debugging is hard to do until the complete project is finished, and the end of the waterfall is reached. Because there is no backwards movement, any bugs discovered during the development process have to be worked around, since there is no backwards movement

## The Modern Way: Agile
If the Waterfall methodology is strict, rigidge, and only moves forward, the Agile methodology is its counterpart. Where the Waterfall way is to only step forward, the Agile way allows for taking one step back in order to take two steps forward. 

There are four main tenants of the Agile methodology, many of which were decided upon because of a direct correlation to the waterfall methodology

1. individuals and interactions over process and tools
    - The waterfall methodology typically involves a large amount of contracts, documentation, and specifications about what tools can and can't be used.
    - The Agile way is to focus more on constant communication and good relations between the client and developer instead of the peripherals
2. working software over comprehensive documentation
    - in the waterfall methodology, even if you know how to fix a bug, if the work to fix it was part of a previous step then too bad: document the bug, create some workarounds, document those workarounds, and give the notes to the client
    - in the Agile methodology, you just go back and fix the mistake, even if it means you need to cut back on some of the features you can release in time
3. Customer collaboration over contract negotiation
    - rigid contracts in the Waterfall methodology made it difficult to change things if/when necessary on both the client and the developers
    - in the Agile methodology there is collaboration between the two, so if changes need to be made the need can be communicated, discussed, and then implemented
4. Responding to change over a plan
    - it is better overall to be able to adjust plans on the fly to actually give the customer what they want than to stick with a plan that does not satisfy anyone

## Agile Methodology
Teams that work with an Agile mindset typically have three things in common:
1. Dynamic Project Scopes
2. Iterative Development Cycles
3. Copious use of Automation Tools

There is some common terminology that is used across Agile methodologies you should be aware of as well:
- Epic
    - Epics are terms used to describe related functionality
        - "Users should be able to manage their authentication with our application"
- User Story
    - User Stories are easy to understand descriptions of functionalty in your application
        - these descriptions should be easy to understand (you don't need technical knowledge to understand them)
    - "as a user I want to login so I can manage my account"
    - typically your user stories will be organized into multiple epics
- Story Points
    - Story Points are arbitrary numbers (as determined by the team) that are assinged to user stories to determine how much time/effort it will take to complete said user story. These numbers are aribtrary because each team will need to decide how to assign story points to user stories
        - for instance, for a login user story, you might assign it three story points due to three potential method calls needed to implement the user story
- Velocity
    - velocity is the number of story points handled (completed) during a development cycle. As a team matures and completes more development cycles, the velocity numbers (both those completed and those aimed for) can be referenced to better understand the capabilities of the team, and to help the team storypoint user stories more accurately in the future
- Sprint
    - development cycles (typically a few weeks in length) are called sprints. During a sprint a velocity will be aimed for, and when the sprint is finished the team can check to see if they met their target velocity or not, which just further helps them to forcast their capabilities in the future

## Agile Implementation: Scrum
Scrum is a methodology that uses the Agile mindset to guide the work process and the way teams interact with clients. There are a couple different roles in a scrum, some for the development team and others to categorize the client:
- Development Team
    - this is the team (usually a small group, no more than 10 individuals) that is doing the work
    - there can be multiple teams, not just a dev team (sre team, it team, etc)
- Product Owner
    - this is the individual or team that handles communication and coordination with the scrum team
- Stakeholder
    - this is the individual or entity that your work is for
- Scrum Master
    - this is who is leading the scrum team: they handle communicating with the product owner and they also manage communication between the different teams in the scrum
        - some teams may not have the Scrum Master handle product owner communication, just be aware
- Team Members (Scrumlings)
    - These are the individuals who compose the various teams in the scrum

## Scrum Terms
