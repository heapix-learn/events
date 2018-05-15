package com.heapix.events.controller;

import com.heapix.events.persistence.model.RegistrationForm;
import com.heapix.events.service.RegistrationFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("reg-form")
public class RegistrationFormController {
    @Autowired
    private RegistrationFormService formService;

    @GetMapping
    public RegistrationForm getRegistrationForm() {
        return formService.getRegistrationForm();
    }

    @PostMapping
    public ResponseEntity<RegistrationForm> saveRegistrationForm(@RequestBody RegistrationForm form) {
        return new ResponseEntity<>(formService.saveRegistrationForm(form), HttpStatus.OK);
    }
}
