package com.heapix.events.controller.bo;

import java.util.Map;

public class EventRegistrationBo {
    private Long id;
    private Map<String, String> info;

    public Map<String, String> getInfo() {
        return info;
    }

    public void setInfo(Map<String, String> info) {
        this.info = info;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventRegistrationBo(Long id, Map<String, String> info) {
        this.id = id;
        this.info = info;
    }
}
