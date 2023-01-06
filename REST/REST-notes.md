# RESTful Web Service
In a REpresentational State Transfer (REST) web service, information that is being passed to and from users and web servers is represented by a "representation" of the data. Typically the "representation" is in JSON formatting, since JSONs are fundamentally just formatted strings, and just about all programming languages are able to work with Strings. You as the end user don't need to know how your information you send to the web server is being transformed/handled, and the web server doesn't need to know how your information was transformed into a JSON and sent to it.

## REST Constraints
there are 6 constraints that any "true" restful web service will adhere to:
1. Client-Server architecture
    - RESTful web services should not handle any client-side logic
    - This allows your application to be tech-agnostic when it comes to the client-side implementation: this is because, no matter what language/tools are used to get information from the end users to your application, a representation will be sent, so it ultimately does not matter to the server what tools are used
2. Stateless
    - RESTful web services don't keep track of their users: they are there to handle converting information into its representation and sending it along its way
3. Cacheable
    - information can be stored clientside for optimization
4. Uniform Interface
    - RESTful web services should have Universal Resource Identifiers (URIs) that match as closely as possible
        - think about your planet api:
            - GET api/planet returns the planets in the database
            - POST api/planet created a new planet and added it to the database
            - DELETE api/planet/{id} deleted a plent
        - You should try and keep your URIs as similar as possible in a RESTful web service, which makes it easier to understand what the route is trying to accomplish in a moment's notice
        - this also means the methods (verbs) associated with the route should be consistent as well
            - GET should always get information
            - POST should always create information
            - etc
5. Layered System
    - RESTful web services should be able to communicate with each other
        - for example, if logging in, the first RESTful web service hit might just validate the login data is in the correct format (no illegal characters, for example) and then send the data on to a second RESTful web service that actually checks if the login data exists or not, and once validation is completed returns a response to the login requester
    - end user does not need to be aware of the layers: they just need to get the end results
6. Code on Demand
    - RESTful web services can return executable code 
        - this is not a common practice, this constraint is considered an optional one