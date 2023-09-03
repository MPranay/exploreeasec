package com.stackroute.TicketBookingService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(basePackages = "com.stackroute.TicketBookingService.model.Booking")
public class TicketBookingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketBookingServiceApplication.class, args);
	}

}
