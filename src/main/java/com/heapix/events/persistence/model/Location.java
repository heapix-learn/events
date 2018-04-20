package com.heapix.events.persistence.model;

import lombok.Data;

/**
 * @author mgergalov
 */
@Data
public class Location {

    private String country;
    private String city;
    private String street;

    public Location(String country, String city, String street) {
        this.country = country;
        this.city = city;
        this.street = street;
    }
}
