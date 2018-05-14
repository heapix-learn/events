package com.heapix.events.controller;

import com.heapix.events.config.security.JwtTokenProvider;
import com.heapix.events.config.security.UserAuth;
import com.heapix.events.controller.bo.CreateResponseBo;
import com.heapix.events.controller.bo.EventRegistrationBo;
import com.heapix.events.controller.bo.UserAdminBo;
import com.heapix.events.controller.dto.NewEventRegistrationDto;
import com.heapix.events.service.EventRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('Super Administrator', 'Administrator', 'Moderator', 'Member')")
    public ResponseEntity<EventRegistrationBo> getEventRegistrationForUser(@PathVariable long id) {
        UserAuth currUser = (UserAuth) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        EventRegistrationBo response = eventRegistrationService.getEventRegistration(id, currUser.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}/users")
    @PreAuthorize("hasAnyAuthority('Super Administrator', 'Administrator', 'Moderator')")
    public ResponseEntity<List<EventRegistrationBo>> getEventRegistrations(@PathVariable long id) {
        List<EventRegistrationBo> response = eventRegistrationService.getEventRegistrations(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('Super Administrator', 'Administrator', 'Moderator', 'Member')")
    public ResponseEntity<CreateResponseBo> registerToEvent(@RequestBody NewEventRegistrationDto registrationDto) throws Exception {
        UserAuth currUser = (UserAuth) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CreateResponseBo response = eventRegistrationService.registerEvent(registrationDto, currUser.getId());
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
