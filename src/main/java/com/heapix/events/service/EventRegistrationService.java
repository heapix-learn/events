package com.heapix.events.service;

import com.heapix.events.controller.bo.CreateResponseBo;
import com.heapix.events.controller.bo.EventRegistrationBo;
import com.heapix.events.controller.bo.UserAdminBo;
import com.heapix.events.controller.dto.NewEventRegistrationDto;

import java.util.List;

/**
 * @author mgergalov
 */
public interface EventRegistrationService {

    CreateResponseBo registerEvent(NewEventRegistrationDto eventRegistration, Long userId) throws Exception;

    EventRegistrationBo getEventRegistration(Long eventId, Long userId);

    void unregisterEvent(Long eventId, Long userId);

    List<EventRegistrationBo> getEventRegistrations(long eventId);
}
