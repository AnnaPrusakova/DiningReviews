package com.example.diningReview.controller;

import com.example.diningReview.model.User;
import com.example.diningReview.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @GetMapping("/{displayName}")
  public User getUserByDisplayName(@PathVariable String displayName) {
    return userService.getUserByDisplayName(displayName);
  }

  @PutMapping("/update/{displayName}")
  public void updateUserData(@PathVariable String displayName, @RequestBody User updateUser) {
    userService.updateUserData(displayName, updateUser);
  }
}
