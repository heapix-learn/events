package com.heapix.events.controller.dto;

import java.util.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author mgergalov
 */
public class UpdateEventDto {

    @NotNull
    @NotEmpty
    private Date date;

    @NotNull
    @NotEmpty
    private String location;

    @NotNull
    @NotEmpty
    private String info;

    private int minNumberOfRegistrations;
    private int maxNumberOfRegistrations;
    private String inputs;
    private Long role;

    public UpdateEventDto(@NotNull @NotEmpty Date date, @NotNull @NotEmpty String location,
                          @NotNull @NotEmpty String info, int minNumberOfRegistrations, int maxNumberOfRegistrations,
                          String inputs, Long role) {
        this.date = date;
        this.location = location;
        this.info = info;
        this.minNumberOfRegistrations = minNumberOfRegistrations;
        this.maxNumberOfRegistrations = maxNumberOfRegistrations;
        this.inputs = inputs;
        this.role = role;
    }

    public UpdateEventDto() {
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
