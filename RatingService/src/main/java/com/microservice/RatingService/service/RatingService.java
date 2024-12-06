package com.microservice.RatingService.service;

import com.microservice.RatingService.entity.Rating;

import java.util.List;

public interface RatingService {
    Rating saveRating(Rating rating);

    List<Rating> getAllRatings();

    List<Rating> getAllRatingsByUserId(String userId);

    List<Rating> getAllRatingsByHotelId(String hotelId);

}
