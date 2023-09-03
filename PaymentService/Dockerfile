FROM openjdk:11
ADD ./target/PaymentService-0.0.1-SNAPSHOT.jar /usr/src/PaymentService-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "PaymentService-0.0.1-SNAPSHOT.jar"]