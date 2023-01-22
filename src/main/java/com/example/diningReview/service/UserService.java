package com.example.diningReview.service;

import com.example.diningReview.model.User;
import com.example.diningReview.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public void createUniqueUser(User user) {

  }
}
