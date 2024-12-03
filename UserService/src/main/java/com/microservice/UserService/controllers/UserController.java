package com.microservice.UserService.controllers;

import com.microservice.UserService.entities.User;
import com.microservice.UserService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> response = this.userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId){
        User response = this.userService.getUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
