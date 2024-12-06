package com.microservice.HotelService.service.serviceimpl;

import com.microservice.HotelService.entities.Hotel;
import com.microservice.HotelService.exceptions.ResourceNotFoundException;
import com.microservice.HotelService.repositories.HotelRepository;
import com.microservice.HotelService.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {
    private HotelRepository hotelRepository;

    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public Hotel getHotel(String id) {
        return this.hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("no hotel with this id"));
    }

    @Override
    public Hotel saveHotel(Hotel hotel) {
        String id = UUID.randomUUID().toString();
        hotel.setId(id);
        return this.hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        List<Hotel> hotels = this.hotelRepository.findAll();
        return hotels;
    }
}
