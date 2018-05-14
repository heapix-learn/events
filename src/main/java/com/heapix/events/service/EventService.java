package com.heapix.events.service;

import com.heapix.events.controller.bo.CreateResponseBo;
import com.heapix.events.controller.bo.EventInfoBo;
import com.heapix.events.controller.bo.UpdateResponseBo;
import com.heapix.events.persistence.model.Event;
import javassist.NotFoundException;

import java.util.List;

/**
 * @author mgergalov
 */
public interface EventService {

    CreateResponseBo createEvent(Event event);
    EventInfoBo getEventInfo(Long id) throws NotFoundException;
    List<EventInfoBo> getAll();
    List<EventInfoBo> getByRole(Long role);
    UpdateResponseBo updateEvent(Event event, Long id);
    void remove(Long id);

}
