package com.example.diningReview.service;

import com.example.diningReview.model.User;
import com.example.diningReview.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public void createUniqueUser(User user) {
    String displayName = user.getDisplayName();
    validateUserName(displayName);

    Optional<User> optionalUser = userRepository.findUserByDisplayName(displayName);
    if (optionalUser.isPresent()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT);
    }
    userRepository.save(user);
  }

  public void updateUserData(String displayName, User updateUser) {
    validateUserName(displayName);
    Optional<User> optionalUser = userRepository.findUserByDisplayName(displayName);
    if (optionalUser.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    User user = optionalUser.get();
    if (!updateUser.getCity().isEmpty()) {
      user.setCity(updateUser.getCity());
    }
    if (!updateUser.getState().isEmpty()) {
      user.setState(updateUser.getState());
    }
    if (!updateUser.getZipCode().isEmpty()) {
      user.setZipCode(user.getZipCode());
    }
    if (updateUser.getDairyWatch().describeConstable().isPresent()) {
      user.setDairyWatch(updateUser.getDairyWatch());
    }
    if (updateUser.getPeanutWatch().describeConstable().isPresent()) {
      user.setPeanutWatch(updateUser.getPeanutWatch());
    }
    if (updateUser.getEggWatch().describeConstable().isPresent()) {
      user.setEggWatch(updateUser.getEggWatch());
    }
    userRepository.save(user);
  }

  public User getUserByDisplayName(String displayName) {
    validateUserName(displayName);
    Optional<User> optionalUser = userRepository.findUserByDisplayName(displayName);

    if (optionalUser.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    return optionalUser.get();
  }

  public void validateUserName(String displayName) {
    if (displayName.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }
}
