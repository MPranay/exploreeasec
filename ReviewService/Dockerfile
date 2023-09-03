FROM openjdk:11
ADD ./target/ReviewService-0.0.1-SNAPSHOT.jar /usr/src/ReviewService-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "ReviewService-0.0.1-SNAPSHOT.jar"]