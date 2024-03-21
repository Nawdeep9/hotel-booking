package com.booking.interview.helpers;

import com.booking.interview.model.City;
import com.booking.interview.model.Hotel;
import lombok.Data;

@Data
public class HotelWrapper implements Comparable<HotelWrapper>{
    private Hotel hotel;
    private Double distanceFromCity;

    public HotelWrapper(Hotel hotel, City city) {
        this.hotel = hotel;
        this.distanceFromCity = calculateDistance(city.getLat(), city.getLon(), hotel.getLat(), hotel.getLon());
    }

    @Override
    public int compareTo(HotelWrapper o) {
        return o.getDistanceFromCity().compareTo(this.getDistanceFromCity());
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // distance between latitudes and longitudes
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        // convert to radians
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // apply formulae
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(lat1) *
                        Math.cos(lat2);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        return rad * c;
    }

}
