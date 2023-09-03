package com.stackroute.TicketBookingService.service;

import com.stackroute.TicketBookingService.model.Booking;

import java.util.List;

public interface BookingService {
    Booking bookTicket(Booking booking);

    List<Booking> getAllBookings();
    boolean deleteBooking(int userId, int locationId);

    List<Booking> getBookingByUserId(int userId);

    List<Booking> getBookingByLocationId(int locationId);

    Booking getBooking(int userId, int locationId);

    // Other methods as needed
}
