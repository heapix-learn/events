package com.heapix.events.controller;

import com.heapix.events.controller.bo.CreateResponseBo;
import com.heapix.events.controller.dto.NewEventRegistrationDto;
import com.heapix.events.service.EventRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author mgergalov
 */
@RestController
@RequestMapping("events/registration")
public class EventRegistrationController {

    @Autowired
    private EventRegistrationService eventRegistrationService;

    @PostMapping
    public ResponseEntity<CreateResponseBo> registerToEvent(@RequestBody NewEventRegistrationDto registrationDto) {
        //impl with userId
        CreateResponseBo response = eventRegistrationService.registerEvent(registrationDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity unregisterEvent(@PathVariable String id) {
        //impl with user id
        eventRegistrationService.unregisterEvent(Long.valueOf(id));
        return new ResponseEntity(HttpStatus.OK);
    }


}
