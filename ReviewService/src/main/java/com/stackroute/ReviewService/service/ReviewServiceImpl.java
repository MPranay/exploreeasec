package com.stackroute.ReviewService.service;

import com.stackroute.ReviewService.model.Review;
import com.stackroute.ReviewService.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class ReviewServiceImpl implements ReviewService{
    private ReviewRepository reviewRepository;
    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review addReview(Review review) {
        Review r = reviewRepository.findByUserIDAndLocationID(review.getUserID(), review.getLocationID());
        if(r==null)
            return reviewRepository.save(review);
        return null;
    }

    @Override
    public List<Review> getReview(int userID) {
        return reviewRepository.findAllByUserID(userID);
    }

    @Override
    public List<Review> getAllUserReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public List<Review> getAllReviewsByLocation(int locationID) {
        return reviewRepository.findAllByLocationID(locationID);
    }


    @Override
    public Review updateReview(Review review, int userID, int locationID) {
        Review r = reviewRepository.findByUserIDAndLocationID(userID, locationID);
        if(r!=null) {
            r.setUserID(review.getUserID());
            r.setLocationID(review.getLocationID());
            r.setContactEmail(review.getContactEmail());
            r.setRating(review.getRating());
            r.setReviewBody(review.getReviewBody());
            r.setName(review.getName());
            return reviewRepository.save(r);
        }
        return null;
    }

    @Override
    public boolean deleteReview(int userID, int locationID) {
        Review r = reviewRepository.findByUserIDAndLocationID(userID, locationID);
        if(r==null)
            return false;
        reviewRepository.delete(r);
        return true;

    }
}
