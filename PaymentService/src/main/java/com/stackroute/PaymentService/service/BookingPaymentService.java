package com.stackroute.PaymentService.service;

import com.stackroute.PaymentService.model.BookingPayment;

import java.util.List;

public interface BookingPaymentService {

    BookingPayment addPayment(BookingPayment bookingPayment);


    BookingPayment getPaymentByUserIdAndLocationId(int userId, int locationId);
    boolean deletePayment(String paymentId);

    BookingPayment getPaymentById(String paymentId);

    List<BookingPayment> getAllPayments();


}
