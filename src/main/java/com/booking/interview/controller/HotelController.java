package com.booking.interview.controller;

import com.booking.interview.model.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.booking.interview.service.HotelService;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    HotelService hotelService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable("id") Integer id) {
        Hotel h = hotelService.getHotelById(id);
        if(h.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(h);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteHotel(@PathVariable("id") Integer id) {
        hotelService.markHotelInactive(id);
        return ResponseEntity.accepted().build();
    }

    @GetMapping(value = "/nearest")
    public ResponseEntity<List<Hotel>> getNearestHotels(@RequestParam(value = "cityId") Integer cityId,
                                                        @RequestParam(value = "num", defaultValue = "1") Integer num) {
        List<Hotel> hotels = hotelService.getNearestHotels(cityId, num);
        if(hotels.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(hotels);
    }
}
