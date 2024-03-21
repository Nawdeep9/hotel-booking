package com.booking.interview.service;

import com.booking.interview.model.Hotel;
import com.booking.interview.repository.CityDao;
import com.booking.interview.repository.HotelDao;
import com.booking.interview.service.Impl.HotelServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.springframework.test.util.AssertionErrors.assertEquals;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
public class HotelServiceTest {

    @Autowired
    private HotelServiceImpl hotelService;

    @MockBean
    private HotelDao hotelDao;

    @MockBean
    private CityDao cityDao;

    Hotel testHotel = new Hotel(1,"test",1d,1d,true);

    @Test
    public void getHotelByIdTest() {
        when(hotelDao.findById(any())).thenReturn(Optional.of(testHotel));
        Hotel hotel = hotelService.getHotelById(1);
        assertEquals("Correct response should be hotel with test name", testHotel, hotel);
    }

    @Test
    public void getHotelByIdFailureTest() {
        when(hotelDao.findById(any())).thenReturn(Optional.empty());
//        Exception exception = Assertions.assertThrows(HotelNotFound.class, () -> hotelService.getHotelById(1));
//        assertTrue("Correct response should be exception with no hotel found message" ,
//                exception.getMessage().contains("No hotel found with matching id"));
    }


}
