# Spring Boot MVC with Thymeleaf and Material Design Lite

Source author [https://github.com/JFelipeP/spring-boot-mdl.git](https://github.com/JFelipeP/spring-boot-mdl.git).

This is an example of Spring Boot MVC with Thymeleaf as its view technology styled with Material Design Lite, an implementation of the Material Design concept created by Google.

Used:
Java 8
Spring Boot 1.5.3.RELEASE
H2 database
thymeleaf
 
## Requirements

- Java 1.8 [set_java.sh](set_java.sh)
- Gradle
- NPM
- Bower

For Node.js/NPM installation visit [https://nodejs.org]. 

Install bower:
 
 ```npm install -g bower```
 
## Running
Install required bower components:

```bower install```

Start the application locally by running:
 
````shell
./gradlew bootRun
````

Or:

```gradle run```

Open [http://localhost:8080] in your browser.

## Login Credentials
    username: user
    password: user
    or
    username: admin
    password: admin


![doc/screen1.png](doc/screen1.png)

![doc/screen2.png](doc/screen2.png)

![doc/screen_left_panel.png](doc/screen_left_panel.png)

## Build

````shell
./gradlew build
````

name of build setting in build.gradle:

````shell
jar {
	baseName = 'mdl'
	version = '0.0.1-SNAPSHOT'
}
````

Run built jar:

````shell
/usr/lib/jvm/java-1.8.0-openjdk-amd64/bin/java -jar build/libs/mdl-0.0.1-SNAPSHOT.jar
````