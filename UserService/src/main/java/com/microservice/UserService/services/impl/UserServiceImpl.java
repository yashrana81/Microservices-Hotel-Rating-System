package com.microservice.UserService.services.impl;

import com.microservice.UserService.entities.User;
import com.microservice.UserService.exceptions.ResourceNotFoundException;
import com.microservice.UserService.repositories.UserRepository;
import com.microservice.UserService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User saveUser(User user) {
        String id = UUID.randomUUID().toString();
        user.setUserId(id);
        return this.userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        return this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("No user found with Id"));
    }
}
