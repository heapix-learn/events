package com.heapix.events.controller.bo;

public class ClubInfoBo {
    Long id;
    String info;

    public ClubInfoBo(Long id, String info) {
        this.id = id;
        this.info = info;
    }

    public ClubInfoBo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
