package com.booking.interview.service;

import com.booking.interview.model.Hotel;

import java.util.List;

public interface HotelService {

     Hotel getHotelById(Integer id);
     void markHotelInactive(Integer id);
     List<Hotel> getNearestHotels(Integer cityId, Integer noOfCities);

}
