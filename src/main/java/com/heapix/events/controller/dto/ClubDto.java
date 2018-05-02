package com.heapix.events.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ClubDto {
    @NotEmpty
    @NotNull
    private String info;

    public ClubDto(@NotEmpty @NotNull String info) {
        this.info = info;
    }

    public ClubDto() {
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
