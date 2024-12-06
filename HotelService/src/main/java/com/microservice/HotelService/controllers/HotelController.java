package com.microservice.HotelService.controllers;

import com.microservice.HotelService.entities.Hotel;
import com.microservice.HotelService.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        this.hotelService.saveHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel);
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels(){
        List<Hotel> response = this.hotelService.getAllHotels();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(path = "/{hotelId}")
    public ResponseEntity<Hotel> getUser(@PathVariable String hotelId){
        Hotel response = this.hotelService.getHotel(hotelId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
