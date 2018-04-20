package com.heapix.events.controller.bo;

import lombok.Data;

/**
 * @author mgergalov
 */
@Data
public class DeleteResponseBo {

    private Long id;

    public DeleteResponseBo(Long id) {
        this.id = id;
    }
}
