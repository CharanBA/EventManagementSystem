package com.spring.ems.repository;

import com.spring.ems.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // Custom query to find tasks by event ID
    List<Task> findByEventId(Long eventId);
}
