package com.heapix.events.persistence.repository;

import com.heapix.events.persistence.model.EventRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author mgergalov
 */
public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Long> {

    EventRegistration findByUserIdAndEventId(Long userId, Long eventId);

}
