package com.heapix.events.controller.bo;

import lombok.Data;

/**
 * @author mgergalov
 */
@Data
public class CreateResponseBo {

    /**
     * id of created element
     */
    private Long id;

    public CreateResponseBo(Long id) {
        this.id = id;
    }
}
