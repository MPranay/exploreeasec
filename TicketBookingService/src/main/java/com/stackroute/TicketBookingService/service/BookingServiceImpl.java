package com.stackroute.TicketBookingService.service;

import com.stackroute.TicketBookingService.model.Booking;
import com.stackroute.TicketBookingService.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking bookTicket(Booking booking) {
        Booking r = bookingRepository.findByUserIdAndLocationId(booking.getUserId(), booking.getLocationId());
        if(r==null)
            return bookingRepository.save(booking);
        return null;
    }

    @Override
    public List<Booking> getBookingByLocationId(int locationId){
        return bookingRepository.findAllByLocationId(locationId);
    }

    @Override
    public Booking getBooking(int userId, int locationId) {
        return bookingRepository.findByUserIdAndLocationId(userId, locationId);
    }

    @Override
    public List<Booking> getBookingByUserId(int userId){

        return bookingRepository.findAllByUserId(userId);
    }


    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }


    @Override
    public boolean deleteBooking(int userId, int locationId) {
        Booking r = bookingRepository.findByUserIdAndLocationId(userId, locationId);
        if(r==null)
            return false;
        bookingRepository.delete(r);
        return true;
    }
}
