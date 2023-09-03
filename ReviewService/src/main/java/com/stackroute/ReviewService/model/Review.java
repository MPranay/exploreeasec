package com.stackroute.ReviewService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document
public class Review {


    @Id
    private String reviewID;
    private int userID;
    private int locationID;


    private String name;
    private String contactEmail;
    private int rating;


    private String reviewBody;


    public Review() {
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewID='" + reviewID + '\'' +
                ", userID=" + userID +
                ", locationID=" + locationID +
                ", name='" + name + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", rating=" + rating +
                ", reviewBody='" + reviewBody + '\'' +
                '}';
    }

    public Review(String reviewID, int userID, int locationID, String name, String contactEmail, int rating, String reviewBody) {
        this.reviewID = reviewID;
        this.userID = userID;
        this.locationID = locationID;
        this.name = name;
        this.contactEmail = contactEmail;
        this.rating = rating;
        this.reviewBody = reviewBody;
    }

    public String getReviewID() {
        return reviewID;
    }

    public void setReviewID(String reviewID) {
        this.reviewID = reviewID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewBody() {
        return reviewBody;
    }

    public void setReviewBody(String reviewBody) {
        this.reviewBody = reviewBody;
    }
}
