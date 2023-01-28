package com.example.diningReview.repository;

import com.example.diningReview.model.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
  Optional<User> findUserByDisplayName(String displayName);

}
