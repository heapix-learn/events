package com.heapix.events.controller.bo;

import lombok.Data;

/**
 * @author mgergalov
 */
@Data
public class UpdateResponseBo {

    /**
     * id of created element
     */
    private Long id;

    public UpdateResponseBo(Long id) {
        this.id = id;
    }

}
