package com.wahuu.restspringdata.service;

import com.wahuu.restspringdata.exceptions.HotelNotExistsException;
import com.wahuu.restspringdata.model.Hotel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelService {

    private List<Hotel> tempHotelsStore = new ArrayList<>();

    public List<Hotel> getAllHotels() {
        return tempHotelsStore;
    }

    public Hotel getHotelByName(String name) throws RuntimeException {
        return tempHotelsStore
                .stream()
                .filter(h -> h.getName().equalsIgnoreCase(name)).findFirst()
                .orElseThrow(() -> new HotelNotExistsException("Cannot find hotel by name"));
    }

    public Long addHotel(Hotel hotel) {
        hotel.setId(1L);
        tempHotelsStore.add(hotel);
        return hotel.getId();
    }

    public Hotel updateHotel(Hotel hotel, Long id) {
        Hotel hotelById = tempHotelsStore
                .stream()
                .filter(h -> h.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cannot find hotel by id"));
        hotel.setId(id);
        tempHotelsStore.remove(hotelById);
        tempHotelsStore.add(hotel);
        return hotel;
    }

    public void deleteHotel(Long id) {
        Hotel hotel = tempHotelsStore
                .stream()
                .filter(h -> h.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cannot find hotel by id"));
        tempHotelsStore.remove(hotel);
    }
}
