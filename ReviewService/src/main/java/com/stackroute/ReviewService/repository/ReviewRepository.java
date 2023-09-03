package com.stackroute.ReviewService.repository;

import com.stackroute.ReviewService.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReviewRepository extends MongoRepository<Review, Integer> {
    List<Review> findAllByUserID(int userID);
    List<Review> findAllByLocationID(int locationID);
    Review findByUserIDAndLocationID(int userID, int locationID);
}
