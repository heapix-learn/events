package com.heapix.events.controller.dto;

/**
 * @author mgergalov
 */
public class UpdateAboutPageDto {

    private String title;
    private String body;

    public UpdateAboutPageDto(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public UpdateAboutPageDto() {
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
