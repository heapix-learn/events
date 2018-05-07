package com.heapix.events.controller;

import com.heapix.events.config.security.JwtTokenProvider;
import com.heapix.events.config.security.UserAuth;
import com.heapix.events.controller.bo.CreateResponseBo;
import com.heapix.events.controller.dto.NewEventRegistrationDto;
import com.heapix.events.persistence.model.User;
import com.heapix.events.service.EventRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private EventRegistrationService eventRegistrationService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('Super Administrator', 'Administrator', 'Moderator', 'Member')")
    public ResponseEntity<CreateResponseBo> registerToEvent(@RequestBody NewEventRegistrationDto registrationDto) {
        //impl with userId
        CreateResponseBo response = eventRegistrationService.registerEvent(registrationDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('Super Administrator', 'Administrator', 'Moderator', 'Member')")
    public ResponseEntity unregisterEvent(@PathVariable long id) {

        UserAuth currUser = (UserAuth) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        eventRegistrationService.unregisterEvent(id, currUser.getId());
        return new ResponseEntity(HttpStatus.OK);
    }


}
