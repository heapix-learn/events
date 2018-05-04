package com.heapix.events.controller;

import com.heapix.events.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mgergalov
 */
@RestController("/users")
public class UserController {

    @Autowired
    private UserService userService;


}
