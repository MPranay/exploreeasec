package com.stackroute.ReviewService.controller;

import com.stackroute.ReviewService.model.Review;
import com.stackroute.ReviewService.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/v4/reviews")
public class ReviewController {
    private ReviewService reviewService;
    @Autowired
    ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }
    //getting all reviews by user id
    @GetMapping("/getById/{userID}")
    public ResponseEntity<?> getById(@PathVariable int userID){
        return new ResponseEntity<>(reviewService.getReview(userID), HttpStatus.OK);
    }

    //getting all the reviews in db
    @GetMapping("/getAllReviews")
    public ResponseEntity<?> getAllReviews(){
        List<Review> all = reviewService.getAllUserReviews();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    //getting all the reviews by location id
    @GetMapping("/getAllReviewsByLocation/{locationID}")
    public ResponseEntity<?> getAllReviewsByLocation(@PathVariable int locationID){
        List<Review> all = reviewService.getAllReviewsByLocation(locationID);
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    //adding a new review
    @PostMapping("/add")
    public ResponseEntity<?> addReview(@RequestBody Review review) {
        //review.setReviewID(null);
        Optional<Review> r = Optional.ofNullable(reviewService.addReview(review));
        if (r.isEmpty())
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //updating a review
    @PutMapping("/update/{userID}/{locationID}")
    public ResponseEntity<?> updateReview(@RequestBody Review review, @PathVariable int userID, @PathVariable int locationID){
        Optional<Review> r = Optional.ofNullable(reviewService.updateReview(review, userID, locationID));
        if(r.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //deleting a review
    @DeleteMapping("/delete/{userID}/{locationID}")
    public ResponseEntity<?> deleteReview(@PathVariable int userID, @PathVariable int locationID){
        boolean r = reviewService.deleteReview(userID, locationID);
        if(r)
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
