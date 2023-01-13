# DevOps Study Guide
DevOps is a blend between Development and Operations. In the context of SREs, you're the bridge between Deveopers and the production environment. Not strictly developers or managers of the production environment: SREs often act as the in-between. Historically, the Development team focused on developing an application and are interested in creating new features to be used by the end users, while the Operations team were the IT part, focusing on the production environment, and making sure that the application that was developed is actually available to the end users.

There has been a lot of friction between Development and Operations teams in the past (and probably still today). The Development team wants rapid changes and high velocity (more features added on quick), whereas the Operations team wants a controlled production environment to reduce the chances of any change dropping the availability of the application. Because of this difference in perspective, these two teams have a constant risk of implementing requirements or imposing restrictions on the other team that are counter-productive at best, harmful for the product at worst. Naturally, this causes issues with the product (app outages, loss of money, etc) and inner-team relationships (teams disliking each other, not communicating, etc). DevOps as a practice is a response to these issues

## Agile DevOps Practices

### Continuous Integration
- The practice of consistently adding code into a single source
    - think merging developer branches into a main branch in a github repo
- This helps prevent developers from writing conflicting code and adding it into the main source code
    - code reviews are a common practice with CI: this further helps to ensure that small errors don't compound into larger ones over time

### Continuous Delivery
- The practice of automating a "reasonable" amount of your DevOps process
    - a typically "full" DevOps process will involve the following steps
        - building the app
        - testing the app
        - deploying the app to a staging environment
        - performing tests on the app and auxiliary resources together (checking web pages interact with app correctly, for instance)
        - deploying the app to the production environment
- There are many reasons you might not want all steps automated (need beta testers to mess around with the app in staging, waiting for "launch" day to release the new feature, etc)

### Continuous Deployment
- This is the all encompassing  DevOps practice where the entire pipeline is automated
    - This model requires robust systems to manage when things go wrong (a build fails, tests don't pass, images are corrupted, etc)
    - This model is useful when you want to get features deployed to end users quickly, no matter how big or small the change