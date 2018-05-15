package com.heapix.events.persistence.repository;

import com.heapix.events.persistence.model.RegistrationForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationFormRepository extends JpaRepository<RegistrationForm, Long> {
}
