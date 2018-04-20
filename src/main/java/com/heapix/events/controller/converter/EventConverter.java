package com.heapix.events.controller.converter;

import com.heapix.events.controller.bo.EventInfoBo;
import com.heapix.events.controller.dto.CreateEventDto;
import com.heapix.events.persistence.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * @author mgergalov
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface EventConverter {

    Event toModel(CreateEventDto event);
    EventInfoBo toInfoBo(Event event);
    List<EventInfoBo> toDtos(List<Event> events);

}
