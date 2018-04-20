package com.heapix.events.persistence.repository;

import com.heapix.events.persistence.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author mgergalov
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
