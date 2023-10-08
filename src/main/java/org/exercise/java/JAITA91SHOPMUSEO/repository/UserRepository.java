package org.exercise.java.JAITA91SHOPMUSEO.repository;

import org.exercise.java.JAITA91SHOPMUSEO.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

}
