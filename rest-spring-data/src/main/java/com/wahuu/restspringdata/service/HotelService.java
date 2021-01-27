package com.wahuu.restspringdata.service;

import com.wahuu.restspringdata.exceptions.HotelNotExistsException;
import com.wahuu.restspringdata.model.Hotel;
import com.wahuu.restspringdata.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel getHotelById(Long id){
        return hotelRepository
                .findById(id)
                .orElseThrow(()-> new HotelNotExistsException(String.format("Cannot find hotel by id %s", id)));
    }

    public Hotel getHotelByName(String name) throws RuntimeException {
        return hotelRepository
                .findByName(name)
                .stream()
                .findFirst()
                .orElseThrow(()-> new HotelNotExistsException(String.format("Cannot find hotel by name %s", name)));
    }

    public Long addHotel(Hotel hotel) {
        hotel.setId(null);
        Hotel save = hotelRepository.save(hotel);
        return save.getId();
    }

    public Hotel updateHotel(Hotel hotel, Long id) {
        Hotel hotelById = getHotelById(id);
        hotel.setId(hotelById.getId());
        return hotelRepository.save(hotel);
    }

    public void deleteHotel(Long id) {
        Hotel hotelById = getHotelById(id);
        hotelRepository.delete(hotelById);
    }
}
