package com.stackroute.PaymentService.controller;

import com.stackroute.PaymentService.model.BookingPayment;
import com.stackroute.PaymentService.service.BookingPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v3/payments")
public class BookingPaymentController {

    private final BookingPaymentService bookingPaymentService;

    @Autowired
    public BookingPaymentController(BookingPaymentService bookingPaymentService) {
        this.bookingPaymentService = bookingPaymentService;
    }

    // Add a new payment
    @PostMapping("/add")
    public ResponseEntity<?> addPayment(@RequestBody BookingPayment bookingPayment) {
        BookingPayment addedPayment = bookingPaymentService.addPayment(bookingPayment);
        if (addedPayment!=null)
            return new ResponseEntity<>(addedPayment, HttpStatus.CREATED);
        return new ResponseEntity<>(addedPayment,HttpStatus.CONFLICT);
    }




    // Delete a payment
    @DeleteMapping("/{paymentId}")
    public ResponseEntity<?> deletePayment(@PathVariable String paymentId) {
        BookingPayment existingPayment = bookingPaymentService.getPaymentById(paymentId);
        if (existingPayment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        bookingPaymentService.deletePayment(paymentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    // Get a payment by paymentId
    @GetMapping("/{paymentId}")
    public ResponseEntity<?> getPaymentById(@PathVariable String paymentId) {
        BookingPayment bookingPayment = bookingPaymentService.getPaymentById(paymentId);

        if (bookingPayment != null) {
            return new ResponseEntity<>(bookingPayment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userId}/{locationId}")
    public ResponseEntity<?> getPaymentByUserIdAndLocationId(@PathVariable int userId, @PathVariable int locationId){
        BookingPayment payment = bookingPaymentService.getPaymentByUserIdAndLocationId(userId, locationId);
        if(payment!=null){
            return new ResponseEntity<>(payment, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

// Get all payments
    @GetMapping("/all")
    public ResponseEntity<?> getAllPayments() {
        List<BookingPayment> bookingPayments = bookingPaymentService.getAllPayments();


        return new ResponseEntity<>(bookingPayments, HttpStatus.OK);


    }
}





