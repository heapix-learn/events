package com.heapix.events.controller.dto;

import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author mgergalov
 */
public class UpdateNewsDto {

    private String body;

    public UpdateNewsDto(String body) {
        this.body = body;
    }

    public UpdateNewsDto() {
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
