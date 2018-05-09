package com.heapix.events.service;

import com.heapix.events.controller.bo.CreateResponseBo;
import com.heapix.events.controller.bo.EventRegistrationBo;
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
    public CreateResponseBo registerEvent(NewEventRegistrationDto dto, Long userId) throws Exception {

        if (eventRegistrationRepository.findByUserIdAndEventId(userId, dto.getEventId()).isPresent()) {
            throw new Exception("user already registered");
        }
        EventRegistration registration = new EventRegistration();
        registration.setUserId(userId);
        registration.setEventId(dto.getEventId());
        EventRegistration response = eventRegistrationRepository.save(registration);
        return new CreateResponseBo(response.getId());
    }

    @Override
    public EventRegistrationBo getEventRegistration(Long eventId, Long userId) {
        EventRegistration event = eventRegistrationRepository.findByUserIdAndEventId(userId, eventId).orElse(null);
        if(event != null) {
            return new EventRegistrationBo(event.getId());
        } else return null;
    }

    @Override
    public void unregisterEvent(Long eventId, Long userId) {
        eventRegistrationRepository.findByUserIdAndEventId(userId, eventId)
                .ifPresent( event -> eventRegistrationRepository.delete(event));
    }

}
