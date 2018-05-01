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
public class Event {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String firstName;
    private Date date;
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


    public Event() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMinNumberOfRegistrations() {
        return minNumberOfRegistrations;
    }

    public void setMinNumberOfRegistrations(int minNumberOfRegistrations) {
        this.minNumberOfRegistrations = minNumberOfRegistrations;
    }

    public int getMaxNumberOfRegistrations() {
        return maxNumberOfRegistrations;
    }

    public void setMaxNumberOfRegistrations(int maxNumberOfRegistrations) {
        this.maxNumberOfRegistrations = maxNumberOfRegistrations;
    }
}
