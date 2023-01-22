package com.example.diningReview.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@RequiredArgsConstructor
public class Restaurant {
  @GeneratedValue
  @Id
  private Long id;

  private String name;
  private String state;
  private String zipCode;
  private String phoneNumber;
  private String website;

  private String overallScore;
  private String peanutScore;
  private String dairyScore;
  private String eggScore;
}
