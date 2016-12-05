# Assignment 3

**Introduction to Service Design and Engineering | University of Trento**

This file aims to provide a short documentation for the third course assignment.  
The original instructions can be found [here](https://sites.google.com/a/unitn.it/introsde_2016-17/lab-sessions/assignments/assignment-3).

The project was developed individually.

Server URL: [https://introsde-a3-server.herokuapp.com/](https://introsde-a3-server.herokuapp.com/)

Client repository: [https://github.com/ShadowTemplate/introsde-2016-assignment-3-client](https://github.com/ShadowTemplate/introsde-2016-assignment-3-client)

Heroku buildpack: [https://github.com/IntroSDE/heroku-buildpack-ant.git](https://github.com/IntroSDE/heroku-buildpack-ant.git)

## Project structure

The project repository is made up of the following *files* and **folders**:
* **src**: application back end 
    * **introsde/assignment**: Java source code
        * **dao**: layer converting model objects from/to TOs
            * *EntityDAO.java*: retrieves model objects by using the persistence layer
            * *ObjectConverter.java*: builds TOs from object models and vice versa
        * **model**: model objects
            *Measure.java*, *Person.java*: POJOs annotated to be persisted via JPA
        * **persistence**: layer handling persistence via JPA
            * *PersistenceManager.java*: singleton that executes JPA operations on data
        * **soap**: SOAP layer for clients 
            * *People.java*: SOAP interface for client methods
            * *PeopleImpl.java*: implementation of the endpoint interface
            * *Publisher.java*: registers SOAP endpoint
        * **to**: model objects
            *Measure.java*, *Person.java*: POJOs annotated to be wrapped into XML data
    * **META-INF**: metadata directory
        * *persistence.xml*: JPA configuration file            
* **WebContent**: web application
    * **META-INF**: metadata directory
    * **WEB-INF**: server resources
        * **lib**: server dependencies
        * *web.xml*: Java EE deployment descriptor
* *build.xml*: Ant configuration file containing task definitions (see next paragraph)
* *ivy.xml*: Ivy configuration file containing server dependencies
* *Procfile*: Heroku task configuration file
* *README.md*: this file
* *system.properties*: Heroku JDK configuration file


The project is composed of two parts: a client and a server. They communicate by exchanging some Transfer Objects.
These are POJOs representing the common interface the two side of the application share.

The client application executes requests to the server and logs the result of these operations into a file.

The server is made up of different packages. Each of them contains Java classes related to a specific layer of the architecture.
Each layer is strongly decoupled from the other non-relevant ones: the soap layer only depends on the DAO one, 
the DAO layer only depends on the persistence one. By doing so, object model classes are not handled by the soap layer
that only uses TOs.


## Project tasks

Some Ant tasks are defined inside *build.xml*. An overview of what each task does follows. Tasks' dependencies are in brackets:
* *download-ivy*: downloads Ivy jar from the Maven repository
* *install-ivy* (*download-ivy*): adds Ivy jar to the working directory
* *resolve* (*install-ivy*): downloads ivy dependencies for server
* *init* (*install-ivy*, *resolve*): initializes server workspace
* *clean*: initializes server workspace
* *install* (*clean*, *init*): compiles server code 
* *start*: register SOAP endpoint; it is executed by Heroku via Procfile


## How to run

Deploy this server on Heroku [via git](https://devcenter.heroku.com/articles/git) and follow the [client instructions](https://github.com/ShadowTemplate/introsde-2016-assignment-3-client/blob/master/README.md).