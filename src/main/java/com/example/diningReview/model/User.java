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
public class User {

  @Id
  @GeneratedValue
  private Long id;

  private String name;
  private String city;
  private String state;
  private String zipCode;

  private Boolean peanutWatch;
  private Boolean dairyWatch;
  private Boolean eggWatch;
}
