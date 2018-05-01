package com.heapix.events.controller.dto;

import com.heapix.events.persistence.model.Location;
import lombok.Data;

import java.util.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    public UpdateEventDto(@NotNull @NotEmpty Date date, @NotNull @NotEmpty String location, @NotNull @NotEmpty String info, int minNumberOfRegistrations, int maxNumberOfRegistrations) {
        this.date = date;
        this.location = location;
        this.info = info;
        this.minNumberOfRegistrations = minNumberOfRegistrations;
        this.maxNumberOfRegistrations = maxNumberOfRegistrations;
    }

    public UpdateEventDto() {
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
