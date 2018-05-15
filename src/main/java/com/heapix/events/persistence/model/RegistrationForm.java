package com.heapix.events.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class RegistrationForm {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private  String inputs;

    public RegistrationForm(String inputs) {
        this.inputs = inputs;
    }

    public RegistrationForm() {
    }

    public String getInputs() {
        return inputs;
    }

    public void setInputs(String inputs) {
        this.inputs = inputs;
    }
}
