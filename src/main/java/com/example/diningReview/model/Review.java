package com.example.diningReview.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "review")
@Getter
@Setter
@RequiredArgsConstructor
public class Review {

  @Id
  @GeneratedValue
  private Long id;

  private String submittedBy;
  private Long restaurantId;
  private String review;

  private Integer peanutScore;
  private Integer dairyScore;
  private Integer eggScore;

  private ReviewStatus status;
}
