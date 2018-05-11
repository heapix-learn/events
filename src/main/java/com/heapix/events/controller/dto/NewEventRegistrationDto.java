package com.heapix.events.controller.dto;

import java.util.Map;

/**
 * @author mgergalov
 */
public class NewEventRegistrationDto {

    private Long eventId;
    private Map<String, String> info;


    public NewEventRegistrationDto(Long eventId) {
        this.eventId = eventId;
    }

    public NewEventRegistrationDto() {
    }


    public Map<String, String> getInfo() {
        return info;
    }

    public void setInfo(Map<String, String> info) {
        this.info = info;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

}
