package com.booking.interview.controller;

import com.booking.interview.model.Hotel;
import com.booking.interview.service.HotelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.containsString;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class HotelControllerTest {

    @MockBean
    private HotelService hotelService;

    @Autowired
    private MockMvc mockMvc;

    Hotel testHotel = new Hotel(1,"test",1d,1d,true);

    @Test
    public void testGetHotelById() throws Exception {

        when(hotelService.getHotelById(any())).thenReturn(testHotel);

        mockMvc
                .perform(get("/hotel/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("test")));
    }

}
