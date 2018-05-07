package com.heapix.events.controller.converter;

import com.heapix.events.controller.bo.EventInfoBo;
import com.heapix.events.controller.dto.CreateEventDto;
import com.heapix.events.controller.dto.UpdateEventDto;
import com.heapix.events.persistence.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author mgergalov
 */
public interface EventConverter {

    Event toModel(CreateEventDto event);
    Event toModel(UpdateEventDto event);


    EventInfoBo toInfoBo(Event event);


    List<EventInfoBo> toDtos(List<Event> events);

}
