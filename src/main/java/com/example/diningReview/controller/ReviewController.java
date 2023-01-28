package com.example.diningReview.controller;

import com.example.diningReview.model.Review;
import com.example.diningReview.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
@AllArgsConstructor
public class ReviewController {

  private final ReviewService reviewService;

  @PostMapping("/add")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void addReviewByUser(@RequestBody Review review) {
    reviewService.addReviewByUser(review);
  }

}
