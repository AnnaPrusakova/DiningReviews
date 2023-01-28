package com.example.diningReview.service;

import com.example.diningReview.model.Restaurant;
import com.example.diningReview.model.Review;
import com.example.diningReview.model.ReviewStatus;
import com.example.diningReview.model.User;
import com.example.diningReview.repository.RestaurantRepository;
import com.example.diningReview.repository.ReviewRepository;
import com.example.diningReview.repository.UserRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class ReviewService {

  private final UserRepository userRepository;
  private final RestaurantRepository restaurantRepository;
  private final ReviewRepository reviewRepository;

  public void addReviewByUser(Review review) {
    validateUserReview(review);

    review.setStatus(ReviewStatus.PENDING);
    reviewRepository.save(review);
  }

  public void validateUserReview(Review review) {
    if (review.getSubmittedBy().isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    Optional<User> optionalUser = userRepository.findUserByDisplayName(review.getSubmittedBy());
    if (optionalUser.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    if (review.getRestaurantId() == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    Optional<Restaurant> optionalRestaurant =
        restaurantRepository.findById(review.getRestaurantId());
    if (optionalRestaurant.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    if (review.getPeanutScore() == null
        && review.getDairyScore() == null
        && review.getEggScore() == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }
}
