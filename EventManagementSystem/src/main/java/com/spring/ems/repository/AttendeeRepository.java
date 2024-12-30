package com.spring.ems.repository;

import com.spring.ems.entity.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, Long> {

    // Find an attendee by their email
    Optional<Attendee> findByEmail(String email);

    // Find all attendees for a specific event
    List<Attendee> findByEvents_Id(Long eventId);

    // Check if an attendee exists by email
    boolean existsByEmail(String email);

    // Find attendee by ID
    Optional<Attendee> findById(Long id);
}
