package com.heapix.events.controller.dto;

/**
 * @author mgergalov
 */
public class NewEventRegistrationDto {

    private Long eventId;

    public NewEventRegistrationDto(Long eventId) {
        this.eventId = eventId;
    }

    public NewEventRegistrationDto() {
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

}
