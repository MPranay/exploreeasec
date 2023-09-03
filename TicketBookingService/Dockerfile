FROM openjdk:11
ADD ./target/TicketBookingService-0.0.1-SNAPSHOT.jar /usr/src/TicketBookingService-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "TicketBookingService-0.0.1-SNAPSHOT.jar"]