package com.heapix.events.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author mgergalov
 */
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

    public CreateNewsDto(@NotEmpty @NotNull String creatorName) {
        this.creatorName = creatorName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

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
