package com.heapix.events.service;

import com.heapix.events.controller.bo.CreateResponseBo;
import com.heapix.events.controller.bo.EventRegistrationBo;
import com.heapix.events.controller.bo.UserAdminBo;
import com.heapix.events.controller.converter.UserConverter;
import com.heapix.events.controller.dto.NewEventRegistrationDto;
import com.heapix.events.persistence.model.EventRegistration;
import com.heapix.events.persistence.repository.EventRegistrationRepository;
import com.heapix.events.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author mgergalov
 */
@Service
public class EventRegistrationServiceImpl implements EventRegistrationService {

    @Autowired
    private EventRegistrationRepository eventRegistrationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public CreateResponseBo registerEvent(NewEventRegistrationDto dto, Long userId) throws Exception {

        if (eventRegistrationRepository.findByUserIdAndEventId(userId, dto.getEventId()).isPresent()) {
            throw new Exception("user already registered");
        }
        EventRegistration registration = new EventRegistration();
        registration.setUserId(userId);
        registration.setEventId(dto.getEventId());
        registration.setInfo(dto.getInfo());
        EventRegistration response = eventRegistrationRepository.save(registration);
        return new CreateResponseBo(response.getId());
    }

    @Override
    public EventRegistrationBo getEventRegistration(Long eventId, Long userId) {
        EventRegistration event = eventRegistrationRepository.findByUserIdAndEventId(userId, eventId).orElse(null);
        if (event != null) {
            return new EventRegistrationBo(event.getId(), event.getInfo(), null);
        } else return null;
    }

    @Override
    public void unregisterEvent(Long eventId, Long userId) {
        eventRegistrationRepository.findByUserIdAndEventId(userId, eventId)
                .ifPresent(event -> eventRegistrationRepository.delete(event));
    }

    @Override
    public List<EventRegistrationBo> getEventRegistrations(long eventId) {
        List<EventRegistration> eventRegistrations = eventRegistrationRepository.findByEventId(eventId);
        List<Long> ids = eventRegistrations.stream()
                .map(EventRegistration::getUserId)
                .collect(Collectors.toList());
        List<UserAdminBo> users = userConverter.toUserAdminBos(userRepository.findAllById(ids));
        return eventRegistrations.stream()
                .map(e -> {
                    UserAdminBo user = users.stream().filter(u -> u.getId().equals(e.getUserId())).findFirst().orElse(null);
                    return new EventRegistrationBo(e.getId(), e.getInfo(), user);
                })
                .collect(Collectors.toList());
    }

}
