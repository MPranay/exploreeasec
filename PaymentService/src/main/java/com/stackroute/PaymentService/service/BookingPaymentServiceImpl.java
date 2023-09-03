package com.stackroute.PaymentService.service;

import com.stackroute.PaymentService.model.BookingPayment;
import com.stackroute.PaymentService.repository.BookingPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingPaymentServiceImpl implements BookingPaymentService {

    private BookingPaymentRepository bookingPaymentRepository;

    @Autowired
    public BookingPaymentServiceImpl(BookingPaymentRepository bookingPaymentRepository) {
        this.bookingPaymentRepository = bookingPaymentRepository;
    }

    @Override
    public BookingPayment addPayment(BookingPayment bookingPayment) {
        Optional<BookingPayment> r = bookingPaymentRepository.findById(bookingPayment.getPaymentId());
        if(r.isEmpty())
            return bookingPaymentRepository.save(bookingPayment);
        return null;
    }

    @Override
    public BookingPayment getPaymentByUserIdAndLocationId(int userId, int locationId) {
        return bookingPaymentRepository.findByUserIdAndLocationId(userId,locationId);
    }


    @Override
    public boolean deletePayment(String paymentId) {
        // Delete the bookingPayment from the database by paymentId
        bookingPaymentRepository.deleteById(paymentId);
        return true;
    }

    @Override
    public BookingPayment getPaymentById(String paymentId) {
        // Retrieve the bookingPayment from the database by paymentId
        Optional<BookingPayment> optionalBookingPayment = bookingPaymentRepository.findById(paymentId);
        return optionalBookingPayment.orElse(null);
    }

    @Override
    public List<BookingPayment> getAllPayments() {
        // Retrieve all bookingPayments from the database
        return bookingPaymentRepository.findAll();
    }
}
