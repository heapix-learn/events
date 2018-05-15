package com.heapix.events.persistence.repository;

import com.heapix.events.persistence.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author mgergalov
 */
@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    List<News> findTop10ByOrderByCreatedDateAsc();
    List<News> findAllByRoleGreaterThanEqual(Long role);

}
