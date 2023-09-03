package com.stackroute.TicketBookingService.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Document
public class Booking {



    @Id
    private String bookingId;
    private int userId;
    private int locationId;

    private int adult;
    private int children;
    private String email;
    private String name;

    private String locationName;
    private long phone;
    private LocalTime timeslot;
    private LocalDate tripdate;

    private Date bookingDate = new Date();
    public Booking() {
    }
    public Booking(String bookingId,LocalTime timeslot, LocalDate tripdate, int userId, int locationId, int adult, int children, String email, String name,String locationName, long phone, Date bookingDate) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.locationId = locationId;
        this.locationName = locationName;
        this.adult = adult;
        this.children = children;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.timeslot = timeslot;
        this.tripdate = tripdate;
        this.bookingDate = bookingDate;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
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


    public int getAdult() {
        return adult;
    }

    public void setAdult(int adult) {
        this.adult = adult;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public LocalTime getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(LocalTime timeslot) {
        this.timeslot = timeslot.now();
    }

    public LocalDate getTripdate() {
        return tripdate;
    }

    public void setTripdate(LocalDate tripdate) {
        this.tripdate = tripdate.now();
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId='" + bookingId + '\'' +
                ", userId=" + userId +
                ", locationId=" + locationId +
                ", adult=" + adult +
                ", children=" + children +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", locationName='" + locationName + '\'' +
                ", phone=" + phone +
                ", timeslot=" + timeslot +
                ", tripdate=" + tripdate +
                ", bookingDate=" + bookingDate +
                '}';
    }
}