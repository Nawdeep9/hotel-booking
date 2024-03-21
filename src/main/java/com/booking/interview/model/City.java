package com.booking.interview.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name= "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "lat")
    private Double lat;

    @Column(name = "lon")
    private Double lon;

    private City() {
    }

    public City(Integer id, String name, Double lat, Double lon) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    public static City emptyCity = new City();

    public boolean isEmpty() {
        return this == emptyCity
                || this.equals(emptyCity)
                || (this.getId() == null && (this.getName() == null || this.getName().isEmpty()));
    }
}
