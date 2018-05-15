package com.heapix.events.service;

import com.heapix.events.persistence.model.RegistrationForm;

public interface RegistrationFormService {
    RegistrationForm getRegistrationForm();
    RegistrationForm saveRegistrationForm(RegistrationForm form);
}
