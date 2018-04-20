package com.heapix.events.controller.bo;

import com.heapix.events.persistence.model.Location;
import lombok.Data;

import java.util.Date;

/**
 * @author mgergalov
 */
@Data
public class EventInfoBo {

    private String firstName;
    private Date date;
    private Location location;
    private String info;
    private int price;
    private int minNumberOfRegistrations;
    private int maxNumberOfRegistrations;

    public EventInfoBo(String firstName, Date date, Location location, String info, int price,
                       int minNumberOfRegistrations, int maxNumberOfRegistrations) {
        this.firstName = firstName;
        this.date = date;
        this.location = location;
        this.info = info;
        this.price = price;
        this.minNumberOfRegistrations = minNumberOfRegistrations;
        this.maxNumberOfRegistrations = maxNumberOfRegistrations;
    }
}
