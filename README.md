# waste-transportation-system
The concept of information system for waste transportation from reserve islands

## Getting Started

These instructions will get you a copy of the project up and running on your local development machine.

### Prerequisites

#### Deployment server configuration:

* OS: Windows 10 or Ubuntu/Debian Linux
* MySQL Community Server 8.0.15
* Apache Tomcat 9.0.19 container
* Node.js 10.15.3
* Optional: MySql Workbench

#### Development machine configuration:

* Intellij Idea 2019.1
* Install other server software

## Build and run application on the development machine

1. Clone **waste-transportation-system** repository.
```
git clone https://github.com/v-gromov/waste-transportation-system
```
2. Run MySQL database according to **api/src/main/resources/application.properties**.
```
Default database name: wtsdb
Default name: springuser
Default password: 123456
```
3. Open the project in Intellij idea
4. Build and run main() function in **impl/src/main/java/one.vladimir.impl/Application.java**
```
REST service will be started on 8080 port
Default link: http://localhost:8080
```
5. Build and run Web interface which sends messages to the REST service
```
Open terminal in **Idea** and write the next lines
cd web
npm install ol
npm install --save-dev parcel-bundler
npm start
```
6. Web interface will be started on 1234 port
```
Default link: http://localhost:1234/
```

## Run application on the deployment server
1. Build and run application in Idea terminal
```
mvn package clean
```
2. Copy **wts_rest_app.war** application to the **tomcat/webapps/** server folder
```
It contains in impl/target folder
```
You can do it via scp on the Linux development machine
```
Some command
```
