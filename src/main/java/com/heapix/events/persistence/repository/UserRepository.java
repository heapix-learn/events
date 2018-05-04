package com.heapix.events.persistence.repository;

import com.heapix.events.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author mgergalov
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    User findByEmail(String email);
    List<User> findByRoleEquals(Long role);
    List<User> findByRoleIsNot(Long role);

}
