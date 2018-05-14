package com.heapix.events.controller.dto;

import java.util.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author mgergalov
 */
public class CreateEventDto {

    @NotNull
    @NotEmpty
    @Size(min = 4, max = 50)
    private String firstName;

    @NotNull
    @NotEmpty
    private Date date;

    @NotNull
    @NotEmpty
    private String location;

    @NotNull
    @NotEmpty
    private String info;

    @NotNull
    @NotEmpty
    private int price;

    private int minNumberOfRegistrations;
    private int maxNumberOfRegistrations;
    private String inputs;
    private Long role;

    public CreateEventDto(@NotNull @NotEmpty @Size(min = 4, max = 50) String firstName, @NotNull @NotEmpty Date date,
                          @NotNull @NotEmpty String location, @NotNull @NotEmpty String info, @NotNull @NotEmpty int price,
                          int minNumberOfRegistrations, int maxNumberOfRegistrations, String inputs, Long role) {
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

    public CreateEventDto() {
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
