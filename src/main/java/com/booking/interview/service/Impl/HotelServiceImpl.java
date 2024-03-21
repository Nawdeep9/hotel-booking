package com.booking.interview.service.Impl;

import com.booking.interview.helpers.HotelWrapper;
import com.booking.interview.model.City;
import com.booking.interview.model.Hotel;
import com.booking.interview.repository.CityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.booking.interview.repository.HotelDao;
import com.booking.interview.service.HotelService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    HotelDao hotelDao;

    @Autowired
    CityDao cityDao;

    @Override
    public Hotel getHotelById(Integer id){ //throws HotelNotFound {
        return hotelDao.findById(id).orElse(Hotel.emptyHotel);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void markHotelInactive(Integer id) {
        //hotelDao.updateHotelStatus(false, id);
        Hotel h = hotelDao.getReferenceById(id);
        h.setActive(false);
        hotelDao.save(h);
    }

    @Override
    public List<Hotel> getNearestHotels(Integer cityId, Integer num)  {
        City city = cityDao.findById(cityId).orElse(City.emptyCity);
        if(!city.isEmpty() && num != 0) {
            List<Hotel> allHotels = hotelDao.findAllByActive(true);
            Queue<HotelWrapper> queue = addFirstNHotelsToQueue(allHotels, city, num);
            checkNAAddRemainingHotels(queue, allHotels, city, num);
            return queue.stream().map(HotelWrapper::getHotel).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
    private Queue<HotelWrapper> addFirstNHotelsToQueue(List<Hotel> allHotels, City city, Integer noOfCities) {
        Queue<HotelWrapper> queue = new PriorityQueue<>();
        for(int i=0; i< noOfCities && i < allHotels.size(); i++) {
            Hotel hotel = allHotels.get(i);
            queue.add(new HotelWrapper(hotel, city));
        }
        return queue;
    }

    private void checkNAAddRemainingHotels(Queue<HotelWrapper> queue, List<Hotel> allHotels, City city, Integer counter) {
        while(counter < allHotels.size()) {
            Hotel hotel = allHotels.get(counter);
            HotelWrapper newHotel = new HotelWrapper(hotel, city);
            if(newHotel.getDistanceFromCity() < queue.peek().getDistanceFromCity()) {
                queue.poll();
                queue.add(newHotel);
            }
            counter++;
        }
    }
}
