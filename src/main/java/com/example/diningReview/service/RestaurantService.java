package com.example.diningReview.service;

import com.example.diningReview.model.Restaurant;
import com.example.diningReview.repository.RestaurantRepository;
import java.util.Optional;
import java.util.regex.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class RestaurantService {
  private final RestaurantRepository restaurantRepository;
  private final Pattern zipCodePattern = Pattern.compile("^[0-9]{5}(?:-[0-9]{4})?$");

  public Restaurant addNewRestaurant(Restaurant restaurant) {
    if (restaurant.getName().isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    if (!zipCodePattern.matcher(restaurant.getZipCode()).matches()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    validateZipCode(restaurant.getZipCode());

    Optional<Restaurant> optionalRestaurant =
        restaurantRepository.findRestaurantsByNameAndZipCode(
            restaurant.getName(), restaurant.getZipCode());
    if (optionalRestaurant.isPresent()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT);
    }

    return restaurantRepository.save(restaurant);
  }

  public Restaurant getRestaurantById(Long id) {
    Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);

    if (optionalRestaurant.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    return optionalRestaurant.get();
  }

  public Iterable<Restaurant> getAllRestaurants() {
    return restaurantRepository.findAll();
  }

  public Iterable<Restaurant> searchRestaurants(String zipcode, String allergy) {
    validateZipCode(zipcode);

    Iterable<Restaurant> restaurants;
    if (allergy.equalsIgnoreCase("peanut")) {
      restaurants =
          restaurantRepository.findRestaurantsByZipCodeAndPeanutScoreNotNullOrderByPeanutScore(
              zipcode);
    } else if (allergy.equalsIgnoreCase("dairy")) {
      restaurants =
          restaurantRepository.findRestaurantsByZipCodeAndDairyScoreNotNullOrderByDairyScore(
              zipcode);
    } else if (allergy.equalsIgnoreCase("eggs")) {
      restaurants =
          restaurantRepository.findRestaurantsByZipCodeAndEggScoreNotNullOrderByEggScore(zipcode);
    } else {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    return restaurants;
  }

  public void validateZipCode(String zipcode) {
    if (!zipCodePattern.matcher(zipcode).matches()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }
}
