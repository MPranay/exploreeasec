package com.stackroute.PaymentService.model;


import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Document
public class BookingPayment {
    private String paymentId;
    private int userId;
    private int locationId;
    private int totalFees;
    private Date transactionDate = new Date();

    public BookingPayment() {
    }

    @Override
    public String toString() {
        return "BookingPayment{" +
                "paymentId=" + paymentId +
                ", userId=" + userId +
                ", locationId=" + locationId +
                ", totalFees=" + totalFees +
                ", transactionDate=" + transactionDate +
                '}';
    }

    public BookingPayment(String paymentId, int userId, int locationId, int totalFees, Date transactionDate) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.locationId = locationId;
        this.totalFees = totalFees;
        this.transactionDate = transactionDate;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getTotalFees() {
        return totalFees;
    }

    public void setTotalFees(int totalFees) {
        this.totalFees = totalFees;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}




