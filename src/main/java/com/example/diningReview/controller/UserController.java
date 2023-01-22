package com.example.diningReview.controller;

import com.example.diningReview.model.User;
import com.example.diningReview.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @PostMapping("/create")
  public void createUniqueUser(User user) {
    userService.createUniqueUser(user);
  }
}
