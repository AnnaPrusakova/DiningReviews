package com.example.diningReview.service;

import com.example.diningReview.model.AdminReviewAction;
import com.example.diningReview.model.Restaurant;
import com.example.diningReview.model.Review;
import com.example.diningReview.model.ReviewStatus;
import com.example.diningReview.repository.RestaurantRepository;
import com.example.diningReview.repository.ReviewRepository;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class AdminService {
  private final ReviewRepository reviewRepository;
  private final RestaurantRepository restaurantRepository;
  private final DecimalFormat decimalFormat = new DecimalFormat("0.00");

  public List<Review> getReviewsByStatus(String reviewStatus) {
    ReviewStatus reviewStatusResult;
    try {
      reviewStatusResult = ReviewStatus.valueOf(reviewStatus.toUpperCase());
    } catch (IllegalArgumentException exception) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    return reviewRepository.findReviewsByStatus(reviewStatusResult);
  }

  public void setReview(Long reviewId, AdminReviewAction adminReviewAction) {
    Optional<Review> optionalReview = reviewRepository.findById(reviewId);
    if (optionalReview.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    Review review = optionalReview.get();
    Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(review.getRestaurantId());
    if (optionalRestaurant.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    if (adminReviewAction.getAccept()) {
      review.setStatus(ReviewStatus.ACCEPTED);
    } else {
      review.setStatus(ReviewStatus.REJECTED);
    }

    reviewRepository.save(review);
    updateRestaurantReviewScores(optionalRestaurant.get());
  }

  private void updateRestaurantReviewScores(Restaurant restaurant) {
    List<Review> reviews = reviewRepository.findReviewsByRestaurantIdAndStatus(restaurant.getId(), ReviewStatus.ACCEPTED);
    if (reviews.size() == 0) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    int peanutSum = 0;
    int peanutCount = 0;
    int dairySum = 0;
    int dairyCount = 0;
    int eggSum = 0;
    int eggCount = 0;

    for (Review review : reviews) {
      if (!ObjectUtils.isEmpty(review.getPeanutScore())) {
        peanutSum += review.getPeanutScore();
        peanutCount++;
      }
      if (!ObjectUtils.isEmpty(review.getDairyScore())) {
        dairySum += review.getDairyScore();
        dairyCount++;
      }
      if (!ObjectUtils.isEmpty(review.getEggScore())) {
        eggSum += review.getEggScore();
        eggCount++;
      }
    }

    int totalCount = peanutCount + dairyCount + eggCount ;
    int totalSum = peanutSum + dairySum + eggSum;

    float overallScore = (float) totalSum / totalCount;
    restaurant.setOverallScore(decimalFormat.format(overallScore));

    if (peanutCount > 0) {
      float peanutScore = (float) peanutSum / peanutCount;
      restaurant.setPeanutScore(decimalFormat.format(peanutScore));
    }

    if (dairyCount > 0) {
      float dairyScore = (float) dairySum / dairyCount;
      restaurant.setDairyScore(decimalFormat.format(dairyScore));
    }

    if (eggCount > 0) {
      float eggScore = (float) eggSum / eggCount;
      restaurant.setEggScore(decimalFormat.format(eggScore));
    }

    restaurantRepository.save(restaurant);
  }

}
