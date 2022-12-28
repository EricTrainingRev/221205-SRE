# Prometheus
Prometheus is an open source monitoring and alerting tool that provides a way to aggregate application metrics within a time-series database and then visualize the data using tools like Grafana.
- A time-series databse adds a timestamp to the data stored, and it is capable of adding other key/value pairs to the data
- Prometheus makes use of PromQL to find data and perform operations upon it
- Prometheus makes use of a pulling system that is called "scrapping". 
- Prometheus is capable of discovering services (finding them on its own) but it can also be configured to target static services (via configurations)
- Prometheus supports multiple options when it comes to graphing/dashboarding the data it stores/calculates via PromQL