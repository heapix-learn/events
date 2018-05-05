package com.heapix.events.controller;

import com.heapix.events.config.security.JwtTokenProvider;
import com.heapix.events.config.security.UserAuth;
import com.heapix.events.controller.bo.CreateResponseBo;
import com.heapix.events.controller.bo.UserAdminBo;
import com.heapix.events.controller.dto.AuthDto;
import com.heapix.events.controller.dto.JwtAuthenticationRequest;
import com.heapix.events.controller.dto.JwtAuthenticationResponse;
import com.heapix.events.controller.dto.RegistrationDto;
import com.heapix.events.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @author mgergalov
 */
@RestController
public class AuthController {

    @Autowired
    public AuthController(JwtTokenProvider jwtTokenProvider,
                          AuthenticationManager authenticationManager,
                          UserService userService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }


    private JwtTokenProvider jwtTokenProvider;
    private AuthenticationManager authenticationManager;
    private UserService userService;


    @PostMapping("/register")
    public CreateResponseBo register(@RequestBody RegistrationDto user) throws Exception {
        return new CreateResponseBo(userService.addUser(user).getId());
    }

    @PutMapping("/register/{id}")
    @PreAuthorize("hasAnyAuthority('Administrator')")
    public void allowRegistration(@NotNull @PathVariable String id) throws NotFoundException {
        userService.allowRegistration(Long.valueOf(id));
    }

    @PostMapping("/auth")
    public ResponseEntity createAuthenticationToken(@RequestBody JwtAuthenticationRequest authRequest) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtTokenProvider.generateToken((UserAuth) authentication.getPrincipal());

        UserAuth currUser = (UserAuth) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserAdminBo user = userService.findUser(currUser.getId());

        return ResponseEntity.ok(new AuthDto(new JwtAuthenticationResponse(jwtToken), user));
    }
}
