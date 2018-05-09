package com.heapix.events.service;

import com.heapix.events.controller.bo.CreateResponseBo;
import com.heapix.events.controller.bo.EventRegistrationBo;
import com.heapix.events.controller.dto.NewEventRegistrationDto;

/**
 * @author mgergalov
 */
public interface EventRegistrationService {

    CreateResponseBo registerEvent(NewEventRegistrationDto eventRegistration, Long userId) throws Exception;
    EventRegistrationBo getEventRegistration(Long eventId, Long userId);
    void unregisterEvent(Long eventId, Long userId);

}
