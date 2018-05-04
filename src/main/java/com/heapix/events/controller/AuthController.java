package com.heapix.events.controller;

import com.heapix.events.config.security.JwtTokenProvider;
import com.heapix.events.controller.dto.JwtAuthenticationRequest;
import com.heapix.events.controller.dto.JwtAuthenticationResponse;
import com.heapix.events.persistence.model.User;
import com.heapix.events.persistence.model.enums.UserRole;
import com.heapix.events.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author mgergalov
 */
@RestController
public class AuthController {

    @Autowired
    public AuthController(PasswordEncoder encoder, UserRepository userRepo,
                          UserDetailsService userDetailsService,
                          JwtTokenProvider jwtTokenProvider,
                          AuthenticationManager authenticationManager) {
        this.encoder = encoder;
        this.userRepo = userRepo;
        this.userDetailsService = userDetailsService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    private PasswordEncoder encoder;
    private UserRepository userRepo;
    private UserDetailsService userDetailsService;
    private JwtTokenProvider jwtTokenProvider;
    private AuthenticationManager authenticationManager;


    @PostMapping("/register")
    public User register(@RequestBody User user) throws Exception {
        User alreadyExist = userRepo.findByEmail(user.getEmail());
        if (alreadyExist == null) {
            user.setPassword(encoder.encode(user.getPassword()));
            user.setRole(UserRole.MEMBER_USER.getId());
            return userRepo.save(user);
        } else throw new Exception("user already registered");

    }

    @PostMapping("/auth")
    public ResponseEntity createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        String token = jwtTokenProvider.generateToken(userDetails);
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}
