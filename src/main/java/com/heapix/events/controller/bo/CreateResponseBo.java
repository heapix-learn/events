package com.heapix.events.controller.bo;

import lombok.Data;

/**
 * @author mgergalov
 */
public class CreateResponseBo {

    /**
     * id of created element
     */
    private Long id;

    public CreateResponseBo(Long id) {
        this.id = id;
    }

    public CreateResponseBo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
