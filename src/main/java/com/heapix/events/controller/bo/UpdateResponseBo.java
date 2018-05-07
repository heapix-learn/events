package com.heapix.events.controller.bo;

import lombok.Data;

/**
 * @author mgergalov
 */
public class UpdateResponseBo {

    /**
     * id of created element
     */
    private Long id;

    public UpdateResponseBo(Long id) {
        this.id = id;
    }

    public UpdateResponseBo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
