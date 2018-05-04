package com.heapix.events.persistence.repository;

import com.heapix.events.persistence.model.EventRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author mgergalov
 */
public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Long> {

    Optional<EventRegistration> findByUserIdAndEventId(Long userId, Long eventId);
}