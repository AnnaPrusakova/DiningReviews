package com.example.diningReview.repository;

import com.example.diningReview.model.Review;
import com.example.diningReview.model.ReviewStatus;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
  List<Review> findReviewsByStatus(ReviewStatus reviewStatus);
  List<Review> findReviewsByRestaurantIdAndStatus(Long restaurantId, ReviewStatus reviewStatus);

}
