package org.exercise.java.JAITA91SHOPMUSEO.service;

import org.exercise.java.JAITA91SHOPMUSEO.model.User;
import org.exercise.java.JAITA91SHOPMUSEO.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) return userOptional.get();
        else throw new UsernameNotFoundException("Username " + username + " not found.");
    }

    public User findById(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) return userOptional.get();
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

}
