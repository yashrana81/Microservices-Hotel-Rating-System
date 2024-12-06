package com.microservice.UserService.entities;

import lombok.Data;

@Data
public class Ratings {
    private int ratingId;
    private String userId;
    private String hotelId;
    private int rating;
    private String feedback;
}
