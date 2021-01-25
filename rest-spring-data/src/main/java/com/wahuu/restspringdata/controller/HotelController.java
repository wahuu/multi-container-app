package com.wahuu.restspringdata.controller;

import com.wahuu.restspringdata.model.Hotel;
import com.wahuu.restspringdata.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping("/hotels")
    public List<Hotel> getHotels() {
        return hotelService.getAllHotels();
    }

    @GetMapping(value = "/hotels/{name}")
    public Hotel getHotelByName(@PathVariable("name") String name) {
        return hotelService.getHotelByName(name);
    }

    @PostMapping("/hotels")
    public ResponseEntity<Long> addHotel(@RequestBody Hotel hotel) {
        Long hotelId = hotelService.addHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelId);
    }

    // add put fpr update
    @PutMapping("/hotels/{id}")
    public Hotel updateHotel(@RequestBody Hotel hotel, @PathVariable("id") Long id) {
        return hotelService.updateHotel(hotel, id);
    }

    @DeleteMapping("/hotels/{id}")
    public ResponseEntity removeHotel(@PathVariable("id") Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
