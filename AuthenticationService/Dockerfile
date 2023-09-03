FROM openjdk:11
ADD ./target/AuthenticationService-0.0.1-SNAPSHOT.jar /usr/src/AuthenticationService-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "AuthenticationService-0.0.1-SNAPSHOT.jar"]