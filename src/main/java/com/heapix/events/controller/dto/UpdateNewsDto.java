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
    private Long role;

    public UpdateNewsDto(String body, Long role) {
        this.body = body;
        this.role = role;
    }

    public UpdateNewsDto() {
    }

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
