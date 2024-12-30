package com.spring.ems.repository;

import com.spring.ems.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    // Find events by name (case-insensitive)
    List<Event> findByNameIgnoreCase(String name);

    // Find events by location
    List<Event> findByLocation(String location);

    // Find events happening after a specific date
    List<Event> findByDateAfter(java.time.LocalDate date);

    // Find events by description containing a keyword (case-insensitive)
    List<Event> findByDescriptionContainingIgnoreCase(String keyword);
}
