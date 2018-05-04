package com.heapix.events.controller.dto;

import java.io.Serializable;

public class JwtAuthenticationResponse implements Serializable {
    private String token;
    private static final Long serialVersionUID = 1250166508152483573L;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getSerialVersionUID() {
        return serialVersionUID;
    }
}

