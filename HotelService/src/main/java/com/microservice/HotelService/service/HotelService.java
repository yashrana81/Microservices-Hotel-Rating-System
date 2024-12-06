package com.microservice.HotelService.service;

import com.microservice.HotelService.entities.Hotel;

import java.util.List;

public interface HotelService {
    Hotel getHotel(String id);
    Hotel saveHotel(Hotel hotel);
    List<Hotel> getAllHotels();
}
