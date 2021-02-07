package com.wahuu.restspringdata.controller;

import com.wahuu.restspringdata.model.Hotel;
import com.wahuu.restspringdata.service.HotelService;
import com.wahuu.restspringdata.service.RateLimitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelController {

    private Logger logger = LoggerFactory.getLogger(HotelController.class);

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RateLimitService rateLimitService;

    @GetMapping("/hotels")
    public List<Hotel> getHotels() {
        rateLimitService.noteApiCall(this.getClass().getName());
        return hotelService.getAllHotels();
    }

    @GetMapping(value = "/hotels/{name}")
    public Hotel getHotelByName(@PathVariable("name") String name) {
        rateLimitService.noteApiCall(this.getClass().getName());
        return hotelService.getHotelByName(name);
    }

    @PostMapping("/hotels")
    public ResponseEntity<Long> addHotel(@RequestBody Hotel hotel) {
        rateLimitService.noteApiCall(this.getClass().getName());
        Long hotelId = hotelService.addHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelId);
    }

    @PutMapping("/hotels/{id}")
    public Hotel updateHotel(@RequestBody Hotel hotel, @PathVariable("id") Long id) {
        rateLimitService.noteApiCall(this.getClass().getName());
        return hotelService.updateHotel(hotel, id);
    }

    @DeleteMapping("/hotels/{id}")
    public ResponseEntity removeHotel(@PathVariable("id") Long id) {
        rateLimitService.noteApiCall(this.getClass().getName());
        hotelService.deleteHotel(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
