# What is HTTP?
Hyper Text Transfer Protocol (http) is the most popular way of transfering information across the web in modern times. It is a request/response system that guarantees a response for every request made. There are a few standard components of an HTTP request you should be familiar with:
- uri
    - this is the part of the request that tells the web server what information you want
- body
    - many http requests are going to contain a body: this is the information that is needed to complete whatever request you are making
- verb
    - the "verb" of an http request provides context for what you as the requester are trying to accomplish with your http request
        - GET: the requester wants to "get" some kind of information
        - POST: typically used when the request wants to create some kind of entry or provide new information to the web application
        - PUT: typically used when the requester wants to update ALL of some resource handled by the web application
        - PATCH: typically when the requester wants to update SOME of a resource handled by the web application
        - DELETE: typically used when the requester wants to delete some resource handled by the web application

You also should be familiar with the following components of an http response:
- body
    - this contains whatever information you requested (or it could be an error message)
- status code [more info on status codes](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status)
    - this is a number that gives an immediate indication of how your request was handled
    - there are multiple http status levels you can get
        - 100 is informational
        - 200 is success
        - 300 is redirect
        - 400 is user error (clientside error)
        - 500 is server error (the developer messed up)
            - 500 is BAD: you do not want 500 status codes being returned to your users

# JSON
Javascript Object Notation (JSON) is one of the most popular ways of formatting information in http requests (really for any sort of web based communication). At their core, jsons are really just formatted strings
```json
{
    "keyOne":"value one",
    "keyTwo":2,
    "keyThree":true
}
```
jsons accept three data types: strings, numbers, and booleans. 