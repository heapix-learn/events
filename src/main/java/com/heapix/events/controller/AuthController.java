package com.heapix.events.controller;

import com.heapix.events.config.security.JwtTokenProvider;
import com.heapix.events.config.security.UserAuth;
import com.heapix.events.controller.bo.CreateResponseBo;
import com.heapix.events.controller.bo.UserAdminBo;
import com.heapix.events.controller.dto.AuthDto;
import com.heapix.events.controller.dto.JwtAuthenticationRequest;
import com.heapix.events.controller.dto.JwtAuthenticationResponse;
import com.heapix.events.controller.dto.RegistrationDto;
import com.heapix.events.persistence.model.User;
import com.heapix.events.persistence.model.enums.UserRole;
import com.heapix.events.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<CreateResponseBo> register(@RequestBody RegistrationDto user) throws Exception {
        CreateResponseBo response = new CreateResponseBo();
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
            response = userService.addUser(user, UserRole.ANONYMOUS_USER.getId());
        } else {
            UserAuth currUser = (UserAuth) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(userService.findUser(currUser.getId()).getRole()<4){
                response = userService.addUser(user, user.getRole());
            }
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/register/{id}")
    @PreAuthorize("hasAnyAuthority('Super Administrator', 'Administrator')")
    public void allowRegistration(@NotNull @PathVariable long id) throws NotFoundException {
        userService.allowRegistration(id);
    }

    @PostMapping("/auth")
    public ResponseEntity<? extends AuthDto> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authRequest) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtTokenProvider.generateToken((UserAuth) authentication.getPrincipal());

        UserAuth currUser = (UserAuth) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserAdminBo user = userService.findUser(currUser.getId());

        return ResponseEntity.ok(new AuthDto(new JwtAuthenticationResponse(jwtToken),user));
    }
}
