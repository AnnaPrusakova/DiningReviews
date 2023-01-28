package com.example.diningReview.controller;

import com.example.diningReview.model.Restaurant;
import com.example.diningReview.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurant")
@AllArgsConstructor
public class RestaurantController {

  private final RestaurantService restaurantService;

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  public Restaurant addNewRestaurant(@RequestBody Restaurant restaurant) {
    return restaurantService.addNewRestaurant(restaurant);
  }

  @GetMapping("/{id}")
  public Restaurant getRestaurantById(@PathVariable Long id) {
    return restaurantService.getRestaurantById(id);
  }

  @GetMapping
  public Iterable<Restaurant> getAllRestaurants() {
    return restaurantService.getAllRestaurants();
  }

  @GetMapping("/search")
  public Iterable<Restaurant> searchRestaurants(@RequestParam String zipcode, @RequestParam String allergy) {
    return restaurantService.searchRestaurants(zipcode, allergy);
  }
}
