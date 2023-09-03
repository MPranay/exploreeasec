package com.stackroute.PaymentService.repository;

import com.stackroute.PaymentService.model.BookingPayment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingPaymentRepository extends MongoRepository<BookingPayment, String> {
    BookingPayment findByUserIdAndLocationId(int userId, int locationId);
}
