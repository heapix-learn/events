package com.heapix.events.controller;

import com.heapix.events.config.security.UserAuth;
import com.heapix.events.persistence.model.enums.UserRole;
import org.springframework.security.core.context.SecurityContextHolder;

class EventUtils {
    static UserRole getUserRole() {
        UserRole role = UserRole.ANONYMOUS_USER;
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
            return UserRole.ANONYMOUS_USER;
        }
        UserAuth currUser = (UserAuth) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (currUser != null) role = UserRole.getByName(currUser.getAuthorities().iterator().next().getAuthority());
        return role;
    }
}
