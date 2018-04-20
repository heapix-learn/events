package com.heapix.events.service;

import com.heapix.events.controller.bo.CreateResponseBo;
import com.heapix.events.controller.bo.EventInfoBo;
import com.heapix.events.controller.bo.UpdateResponseBo;
import com.heapix.events.controller.converter.EventConverter;
import com.heapix.events.persistence.model.Event;
import com.heapix.events.persistence.repository.EventRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mgergalov
 */
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventConverter eventConverter;

    @Override
    public EventInfoBo getEventInfo(Long id) throws NotFoundException {
        Event response = eventRepository.getOne(id);
        return eventConverter.toInfoBo(response);
    }

    @Override
    public List<EventInfoBo> getAll() {
        List<Event> events = eventRepository.findAll();
        return eventConverter.toDtos(events);
    }

    @Override
    public UpdateResponseBo updateEvent(Event event) {
        return new UpdateResponseBo(eventRepository.save(eventRepository.getOne(event.getId())).getId());
    }

    @Override
    public CreateResponseBo createEvent(Event event) {
        return null;
    }

    @Override
    public void remove(Long id) {
        eventRepository.deleteById(id);
    }
}
