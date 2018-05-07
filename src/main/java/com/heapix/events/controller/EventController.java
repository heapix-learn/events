package com.heapix.events.controller;

import com.heapix.events.controller.bo.CreateResponseBo;
import com.heapix.events.controller.bo.EventInfoBo;
import com.heapix.events.controller.converter.EventConverter;
import com.heapix.events.controller.dto.CreateEventDto;
import com.heapix.events.controller.dto.UpdateEventDto;
import com.heapix.events.persistence.model.Event;
import com.heapix.events.service.EventService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author mgergalov
 */
@RestController
@RequestMapping("events")
public class EventController {

    @Autowired
    private EventService eventService;
    private EventConverter eventConverter;


    @GetMapping
    @PreAuthorize("hasAnyAuthority('Super Administrator', 'Administrator', 'Moderator', 'Member')")
    public List<EventInfoBo> getAllEvents() {
        //impl
        return eventService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('Super Administrator', 'Administrator', 'Moderator', 'Member')")
    public EventInfoBo getEventInfo(@NotNull @PathVariable String id) throws NotFoundException {
        return eventService.getEventInfo(Long.valueOf(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('Super Administrator', 'Administrator', 'Moderator')")
    public ResponseEntity addEvent(@RequestBody CreateEventDto eventDto) {
        //impl
        CreateResponseBo response = eventService.createEvent(eventConverter.toModel(eventDto));
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('Super Administrator', 'Administrator', 'Moderator')")
    public ResponseEntity updateEvent(@NotEmpty @RequestBody UpdateEventDto eventDto,
                                      @PathVariable String id) {
        //impl
        Event event = eventConverter.toModel(eventDto);
        return new ResponseEntity(eventService.updateEvent(event,Long.valueOf(id)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('Super Administrator', 'Administrator', 'Moderator')")
    public ResponseEntity removeEvent(@PathVariable String id) {
        //impl
        eventService.remove(Long.valueOf(id));
        return new ResponseEntity(HttpStatus.OK);
    }

}
