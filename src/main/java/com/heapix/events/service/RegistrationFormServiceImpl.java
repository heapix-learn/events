package com.heapix.events.service;

import com.heapix.events.persistence.model.RegistrationForm;
import com.heapix.events.persistence.repository.RegistrationFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationFormServiceImpl implements RegistrationFormService {

    @Autowired
    private RegistrationFormRepository formRepository;

    @Override
    public RegistrationForm getRegistrationForm() {
        List<RegistrationForm> forms = formRepository.findAll();
        if(forms.isEmpty()){
            return null;
        } else {
            return forms.get(0);
        }
    }

    @Override
    public RegistrationForm saveRegistrationForm(RegistrationForm form) {
        RegistrationForm entity = getRegistrationForm();
        if (entity != null) {
            entity.setInputs(form.getInputs());
        } else entity = form;
        return formRepository.save(entity);
    }
}
