package com.heapix.events.service;

import com.heapix.events.controller.bo.CreateResponseBo;
import com.heapix.events.controller.dto.NewEventRegistrationDto;

/**
 * @author mgergalov
 */
public interface EventRegistrationService {

    CreateResponseBo registerEvent(NewEventRegistrationDto eventRegistration);
    void unregisterEvent(Long eventId, Long userId);

}
