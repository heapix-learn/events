package com.heapix.events.controller.dto;

import com.heapix.events.controller.bo.UserAdminBo;
import com.heapix.events.persistence.model.enums.UserRole;

/**
 * @author mgergalov
 */
public class AuthDto {

    private JwtAuthenticationResponse auth;
    private Long id;
    private String firstName;
    private String role;

    public AuthDto(JwtAuthenticationResponse auth, UserAdminBo user) {
        this.auth = auth;
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.role = UserRole.getById(user.getRole()).getRole();
    }

    public AuthDto() {
    }

    public JwtAuthenticationResponse getAuth() {
        return auth;
    }

    public void setAuth(JwtAuthenticationResponse auth) {
        this.auth = auth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
