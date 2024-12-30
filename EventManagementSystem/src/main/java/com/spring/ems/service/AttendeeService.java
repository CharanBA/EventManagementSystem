package com.spring.ems.service;


import com.spring.ems.entity.Attendee;
import com.spring.ems.repository.AttendeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendeeService {

    private final AttendeeRepository attendeeRepository;

    @Autowired
    public AttendeeService(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    public void saveAttendee(Attendee attendee) {
        attendeeRepository.save(attendee);
    }

    public Optional<Attendee> findById(Long id) {
        return attendeeRepository.findById(id);
    }

    public List<Attendee> findAttendeesByEvent(Long eventId) {
        return attendeeRepository.findByEvents_Id(eventId);
    }

    public void deleteAttendee(Long id) {
        attendeeRepository.deleteById(id);
    }

    public boolean existsByEmail(String email) {
        return attendeeRepository.existsByEmail(email);
    }
}
