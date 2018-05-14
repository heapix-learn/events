package com.heapix.events.controller.bo;

import java.util.Map;

public class EventRegistrationBo {
    private Long id;
    private String info;
    private UserAdminBo user;

    public UserAdminBo getUser() {
        return user;
    }

    public void setUser(UserAdminBo user) {
        this.user = user;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventRegistrationBo(Long id, String info, UserAdminBo user) {
        this.id = id;
        this.info = info;
        this.user = user;
    }
}
