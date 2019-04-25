# Waste Transportation System (WTS)
The concept of the information system for waste transportation from reserve islands

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes

### Prerequisites

#### Server configuration

* OS: Windows 10 or Ubuntu/Debian Linux
* MySQL Community Server 8.0.15
* Apache Tomcat 9.0.19 container
* Node.js 10.15.3
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
5. Build and run JS Web application which sends messages to the REST service.
Open terminal in the **Idea** and write the next commands
```
cd web
npm install ol
npm install --save-dev parcel-bundler
npm start
```
6. Web interface will be run on the 1234 port
```
Default link http://localhost:1234/
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
