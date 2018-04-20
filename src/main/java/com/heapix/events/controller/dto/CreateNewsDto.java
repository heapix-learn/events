package com.heapix.events.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author mgergalov
 */
@Data
public class CreateNewsDto {

    @NotEmpty
    @NotNull
    private String creatorName;
    @NotEmpty
    @NotNull
    private String title;
    @NotEmpty
    @NotNull
    private String body;

}
