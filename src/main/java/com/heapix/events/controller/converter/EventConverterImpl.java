package com.heapix.events.controller.converter;

import com.heapix.events.controller.bo.EventInfoBo;
import com.heapix.events.controller.dto.CreateEventDto;
import com.heapix.events.controller.dto.UpdateEventDto;
import com.heapix.events.persistence.model.Event;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author mgergalov
 */
@Component
public class EventConverterImpl implements EventConverter {
    public EventConverterImpl() {
    }

    public Event toModel(CreateEventDto event) {
        if (event == null) {
            return null;
        } else {
            Event event_ = new Event();
            event_.setFirstName(event.getFirstName());
            event_.setDate(event.getDate());
            event_.setLocation(event.getLocation());
            event_.setInfo(event.getInfo());
            event_.setPrice(event.getPrice());
            event_.setMinNumberOfRegistrations(event.getMinNumberOfRegistrations());
            event_.setMaxNumberOfRegistrations(event.getMaxNumberOfRegistrations());
            event_.setInputs(event.getInputs());
            event_.setRole(event.getRole());
            return event_;
        }
    }

    public Event toModel(UpdateEventDto event) {
        if (event == null) {
            return null;
        } else {
            Event event_ = new Event();
            event_.setDate(event.getDate());
            event_.setLocation(event.getLocation());
            event_.setInfo(event.getInfo());
            event_.setMinNumberOfRegistrations(event.getMinNumberOfRegistrations());
            event_.setMaxNumberOfRegistrations(event.getMaxNumberOfRegistrations());
            event_.setInputs(event.getInputs());
            event_.setRole(event.getRole());
            return event_;
        }
    }

    public EventInfoBo toInfoBo(Event event) {
        if (event == null) {
            return null;
        } else {
            EventInfoBo eventInfoBo = new EventInfoBo();
            eventInfoBo.setId(event.getId());
            eventInfoBo.setFirstName(event.getFirstName());
            eventInfoBo.setDate(event.getDate());
            eventInfoBo.setLocation(event.getLocation());
            eventInfoBo.setInfo(event.getInfo());
            eventInfoBo.setPrice(event.getPrice());
            eventInfoBo.setMinNumberOfRegistrations(event.getMinNumberOfRegistrations());
            eventInfoBo.setMaxNumberOfRegistrations(event.getMaxNumberOfRegistrations());
            eventInfoBo.setFields(event.getInputs());
            return eventInfoBo;
        }
    }

    public List<EventInfoBo> toDtos(List<Event> events) {
        if (events == null) {
            return null;
        } else {
            List<EventInfoBo> list = new ArrayList();
            Iterator var3 = events.iterator();

            while(var3.hasNext()) {
                Event event = (Event)var3.next();
                list.add(this.toInfoBo(event));
            }

            return list;
        }
    }
}