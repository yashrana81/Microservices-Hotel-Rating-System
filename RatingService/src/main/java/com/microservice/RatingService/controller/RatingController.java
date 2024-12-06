package com.microservice.RatingService.controller;

import com.microservice.RatingService.entity.Rating;
import com.microservice.RatingService.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    ResponseEntity<Rating> saveRating(@RequestBody Rating rating){
        this.ratingService.saveRating(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(rating);
    }

    @GetMapping("/hotels/{hotelId}")
    ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable(value = "hotelId") String hotelId){
        return ResponseEntity.ok(this.ratingService.getAllRatingsByHotelId(hotelId));
    }

    @GetMapping("/{userId}")
    ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId){
        return ResponseEntity.status(HttpStatus.OK).body(this.ratingService.getAllRatingsByUserId(userId));
    }

    @GetMapping
    ResponseEntity<List<Rating>> getRatings(){
        return ResponseEntity.status(HttpStatus.OK).body(this.ratingService.getAllRatings());
    }
}
