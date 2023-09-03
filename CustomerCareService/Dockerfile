FROM openjdk:11
ADD ./target/CustomerCareService-0.0.1-SNAPSHOT.jar /usr/src/CustomerCareService-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "CustomerCareService-0.0.1-SNAPSHOT.jar"]