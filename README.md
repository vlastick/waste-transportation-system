## Table of contents
* [General info](#waste-transportation-system)
* [Technologies](#technologies)
* [Demo](#demo)
* [Getting started](#getting-started)
* [Application building on the local machine](#build-and-run-the-application-on-the-local-machine)
* [Application deloyment on the server](#run-the-application-on-the-deployment-server)
* [Authors](#authors)
* [License](#license)

# Waste Transportation System

The concept of the information system for waste transportation service which is used to pick up the tourists garbage from islands and deliver one to the recycling point.
* Simplifies tourism and makes rest better
* Reduces the number of dumps and forest fires
* Saves the nature and environment in reserves

User roles
* **Tourist** makes an order to the service
* **Crewman** gets tourist's request and picks up the garbage to the boat
* **Manager** explores statistics with boats' routes and tourists' points

### Technologies
* Java 11
* Spring Boot
* MySQL
* Hibernate
* Maven
* JavaScript
* OpenStreetMap
* Leaflet
* BootStrap

More information is available on the project presentation.
## Demo
### Tourist's endpoints
User adds few dumps and makes a request.\
<IMG>

### Crewman's endpoints
User sets boat position and then gets the nearest points. The route contains not more than 3 points. After processing the ship goes to the base to unload the garbage.\
<IMG>

### Manager's endpoints
User explores statistics on the map using the filter.\
<IMG>

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes

### Prerequisites

#### Server configuration

* OS: Windows 10 or Ubuntu/Debian Linux
* MySQL Community Server 8.0.15
* Apache Tomcat 9.0.19 container
* Optional: MySql Workbench 8.0.15

#### Local machine configuration

* Intellij Idea 2019.1
* Server software

## Build and run the application on the local machine

1. Clone the **waste-transportation-system** repository
```
git clone https://github.com/v-gromov/waste-transportation-system
```
2. Run MySQL database according to the **api/src/main/resources/application.properties**
```
Default database name: wtsdb
Default name: springuser
Default password: 123456
```
3. Open the project in the Intellij Idea
4. Build and run Java REST service
```
Main function is located in impl/src/main/java/one.vladimir.impl/Application.java
```
5. REST service will be run on the 8080 port
```
Default link http://localhost:8080
```
6. Web interface will be run on the 8080 port
```
Default link http://localhost:8080/
```

## Run the application on the deployment server
1. Build the application in the Idea terminal on your local machine
```
mvn package clean
```
2. Copy **wts_app.war** application to the **tomcat/webapps/** server folder
```
wts_rest_app.war is located in impl/taget/wts_app.war
```
You can do it via scp on the Linux development machine where tomcat is located in the server home folder
```
scp wts_app.war username@server_ip:~/tomcat/webapps
```
## Authors
Vladimir Gromov\
Viktor40000
## Licence
SMT
