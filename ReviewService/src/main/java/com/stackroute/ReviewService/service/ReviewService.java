package com.stackroute.ReviewService.service;

import com.stackroute.ReviewService.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getReview(int userID);
    List<Review> getAllUserReviews();
    List<Review> getAllReviewsByLocation(int locationID);
    Review addReview(Review review);
    Review updateReview(Review review, int userID, int locationID);
    boolean deleteReview(int userID, int locationID);

}
