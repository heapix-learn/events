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

@Data
public class UpdateEventDto {

    @NotNull
    @NotEmpty
    @Size(min = 4, max = 50)
    private String firstName;

    @NotNull
    @NotEmpty
    private Date date;

    @NotNull
    @NotEmpty
    private Location location;

    @NotNull
    @NotEmpty
    private String info;

    @NotNull
    @NotEmpty
    private int price;

    private int minNumberOfRegistrations;
    private int maxNumberOfRegistrations;

}
