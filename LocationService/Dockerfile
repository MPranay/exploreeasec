FROM openjdk:11
ADD ./target/LocationService-0.0.1-SNAPSHOT.jar /usr/src/LocationService-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "LocationService-0.0.1-SNAPSHOT.jar"]