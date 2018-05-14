package com.heapix.events.controller.bo;

import java.util.Date;

/**
 * @author mgergalov
 */
public class EventInfoBo {

    private Long id;
    private String firstName;
    private Date date;
    private String location;
    private String info;
    private int price;
    private int minNumberOfRegistrations;
    private int maxNumberOfRegistrations;
    private String inputs;
    private Long role;

    public EventInfoBo(Long id, String firstName, Date date, String location, String info, int price,
                       int minNumberOfRegistrations, int maxNumberOfRegistrations, String inputs, Long role) {
        this.id = id;
        this.firstName = firstName;
        this.date = date;
        this.location = location;
        this.info = info;
        this.price = price;
        this.minNumberOfRegistrations = minNumberOfRegistrations;
        this.maxNumberOfRegistrations = maxNumberOfRegistrations;
        this.inputs = inputs;
        this.role = role;
    }

    public EventInfoBo() {
    }

    public String getInputs() {
        return inputs;
    }

    public void setInputs(String inputs) {
        this.inputs = inputs;
    }

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
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
