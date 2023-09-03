FROM openjdk:11
ADD ./target/NotificationService-0.0.1-SNAPSHOT.jar /usr/src/NotificationService-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "NotificationService-0.0.1-SNAPSHOT.jar"]