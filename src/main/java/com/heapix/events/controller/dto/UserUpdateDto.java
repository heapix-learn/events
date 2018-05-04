package com.heapix.events.controller.dto;

import javax.persistence.Column;

/**
 * @author mgergalov
 */
public class UserUpdateDto {

    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String phone1;
    private String phone2;

    public UserUpdateDto(String email, String firstName, String lastName, String password, String phone1, String phone2) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phone1 = phone1;
        this.phone2 = phone2;
    }

    public UserUpdateDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }
}
