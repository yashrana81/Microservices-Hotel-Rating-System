package com.microservice.UserService.services.impl;

import com.microservice.UserService.entities.Hotel;
import com.microservice.UserService.entities.Rating;
import com.microservice.UserService.entities.User;
import com.microservice.UserService.exceptions.ResourceNotFoundException;
import com.microservice.UserService.repositories.UserRepository;
import com.microservice.UserService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;
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
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("No user found with Id"));
//        String urlRating = "http://localhost:8080/ratings/"+userId;
        String urlRating = "http://RATING-SERVICE/ratings/"+userId;

//        Rating[] userRatings = this.restTemplate.getForObject(urlRating,Rating[].class);
//        List<Rating> ratings = Arrays.asList(userRatings);

        ResponseEntity<List<Rating>> userRatings = this.restTemplate.exchange(urlRating, HttpMethod.GET, null, new ParameterizedTypeReference<List<Rating>>() {
        });
        List<Rating>ratings = userRatings.getBody();
        String urlHotel ="http://HOTEL-SERVICE/hotels/";

        List<Rating> ratingList = ratings.stream().map(rating -> {
            System.out.println(rating.getHotelId());
            ResponseEntity<Hotel> hotelResponseEntity = this.restTemplate.getForEntity(urlHotel+rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelResponseEntity.getBody();
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return user;
    }
}
