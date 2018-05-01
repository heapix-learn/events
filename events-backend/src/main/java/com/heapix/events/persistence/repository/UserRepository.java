package com.heapix.events.persistence.repository;

import com.heapix.events.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author mgergalov
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    User findByEmail(String email);

}
