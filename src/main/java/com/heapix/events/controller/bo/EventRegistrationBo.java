package com.heapix.events.controller.bo;

public class EventRegistrationBo {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventRegistrationBo(Long id) {
        this.id = id;
    }
}
