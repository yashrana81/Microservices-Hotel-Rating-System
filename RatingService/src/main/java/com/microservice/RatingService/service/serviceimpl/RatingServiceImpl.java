package com.microservice.RatingService.service.serviceimpl;

import com.microservice.RatingService.entity.Rating;
import com.microservice.RatingService.respository.RatingRepository;
import com.microservice.RatingService.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;
    @Override
    public Rating saveRating(Rating rating) {
        return this.ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return this.ratingRepository.findAll();
    }

    @Override
    public List<Rating> getAllRatingsByUserId(String userId) {
        return this.ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getAllRatingsByHotelId(String hotelId) {
        return this.ratingRepository.findByHotelId(hotelId);
    }
}
