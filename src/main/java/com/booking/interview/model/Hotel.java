package com.booking.interview.model;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name= "hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "lat")
    private Double lat;

    @Column(name = "lon")
    private Double lon;

    @Column(name = "active")
    private Boolean active;

    private Hotel() {
    }

    public static Hotel emptyHotel = new Hotel();

    public Hotel(Integer id, String name, Double lat, Double lon, Boolean active) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.active = active;
    }

    public boolean isEmpty() {
        return this == emptyHotel
                || this.equals(emptyHotel)
                || (this.getId() == null && (this.getName() == null || this.getName().isEmpty()));
    }
}
