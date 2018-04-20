package com.heapix.events.persistence.model;

import lombok.Data;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author mgergalov
 */
@Entity
@Data
public class Event {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String firstName;
    private Date date;
    @OneToOne
    private Location location;
    private String info;
    private int price;
    private int minNumberOfRegistrations;
    private int maxNumberOfRegistrations;

    public Event(String firstName, Date date, Location location, String info, int price, int minNumberOfRegistrations,
                 int maxNumberOfRegistrations) {
        this.firstName = firstName;
        this.date = date;
        this.location = location;
        this.info = info;
        this.price = price;
        this.minNumberOfRegistrations = minNumberOfRegistrations;
        this.maxNumberOfRegistrations = maxNumberOfRegistrations;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", date=" + date +
                ", location=" + location +
                ", info='" + info + '\'' +
                ", price=" + price +
                ", minNumberOfRegistrations=" + minNumberOfRegistrations +
                ", maxNumberOfRegistrations=" + maxNumberOfRegistrations +
                '}';
    }
}
