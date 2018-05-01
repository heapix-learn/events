package com.heapix.events.persistence.model;

import com.heapix.events.persistence.model.enums.UserRole;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author mgergalov
 */
@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone1;
    private String phone2;
    private String password;
    private UserRole role;
    private String annotations;

}
