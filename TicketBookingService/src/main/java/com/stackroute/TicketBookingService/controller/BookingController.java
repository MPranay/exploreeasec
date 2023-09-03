package com.stackroute.TicketBookingService.controller;

import com.stackroute.TicketBookingService.model.Booking;
import com.stackroute.TicketBookingService.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v5/bookings")
public class BookingController {
    private final BookingService bookingService;
    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBooking(@RequestBody Booking booking) {

        Optional<Booking> r = Optional.ofNullable(bookingService.bookTicket(booking));
        if (r.isEmpty())
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<?> getByUserId(@PathVariable int userId){
        List<Booking> all = bookingService.getBookingByUserId(userId);
        return new ResponseEntity<>(all,HttpStatus.OK);
    }

    @GetMapping("/getByLocationId/{locationId}")
    public ResponseEntity<?> getByLocationId(@PathVariable int locationId){
        List<Booking> all = bookingService.getBookingByLocationId(locationId);
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/getBooking/{userId}/{locationId}")
    public ResponseEntity<?> getBooking(@PathVariable int userId, @PathVariable int locationId){
        return new ResponseEntity<>(bookingService.getBooking(userId, locationId), HttpStatus.OK);
    }

    @DeleteMapping("/deleteBooking/{userId}/{locationId}")
    public ResponseEntity<?> deleteBooking(@PathVariable int userId, @PathVariable int locationId){
        boolean r = bookingService.deleteBooking(userId, locationId);
        if(r)
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
