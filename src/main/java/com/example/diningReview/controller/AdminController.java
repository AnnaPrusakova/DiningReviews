package com.example.diningReview.controller;

import com.example.diningReview.model.AdminReviewAction;
import com.example.diningReview.model.Review;
import com.example.diningReview.service.AdminService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
  private final AdminService adminService;

  @GetMapping("/reviews")
  public List<Review> getReviewsByStatus(@RequestParam String reviewStatus) {
    return adminService.getReviewsByStatus(reviewStatus);
  }

  @PutMapping("/reviews/{reviewId}")
  public void setReview(
      @PathVariable Long reviewId, @RequestBody AdminReviewAction adminReviewAction) {
    adminService.setReview(reviewId, adminReviewAction);
  }
}
