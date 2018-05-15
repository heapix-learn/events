package com.heapix.events.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author mgergalov
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CreateNewsDto {

    @NotEmpty
    @NotNull
    private String title;
    @NotEmpty
    @NotNull
    private String body;
    @NotNull
    private  Long role;

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }

    public CreateNewsDto() { }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
