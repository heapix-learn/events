package com.heapix.events.service;

import com.heapix.events.controller.bo.CreateResponseBo;
import com.heapix.events.controller.dto.NewEventRegistrationDto;
import com.heapix.events.persistence.model.EventRegistration;
import com.heapix.events.persistence.repository.EventRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author mgergalov
 */
@Service
public class EventRegistrationServiceImpl implements EventRegistrationService {

    @Autowired
    private EventRegistrationRepository eventRegistrationRepository;

    @Override
    public CreateResponseBo registerEvent(NewEventRegistrationDto dto) {
        EventRegistration registration = new EventRegistration();
        registration.setUserId(1l);
        registration.setEventId(dto.getEventId());
        EventRegistration response = eventRegistrationRepository.save(registration);
        return new CreateResponseBo(response.getId());
    }

    @Override
    public void unregisterEvent(Long eventId, Long userId) {

        Optional<EventRegistration> event = eventRegistrationRepository.findByUserIdAndEventId(eventId, userId);
        event.ifPresent(value -> eventRegistrationRepository.delete(value));
    }

}
