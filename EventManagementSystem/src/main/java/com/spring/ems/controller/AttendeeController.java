package com.spring.ems.controller;

import com.spring.ems.entity.Attendee;
import com.spring.ems.service.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attendees")
public class AttendeeController {

    private final AttendeeService attendeeService;

    @Autowired
    public AttendeeController(AttendeeService attendeeService) {
        this.attendeeService = attendeeService;
    }

    // Add a new attendee
    @PostMapping
    public ResponseEntity<String> addAttendee(@Valid @RequestBody Attendee attendee, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>("Invalid attendee data", HttpStatus.BAD_REQUEST);
        }

        if (attendeeService.existsByEmail(attendee.getEmail())) {
            return new ResponseEntity<>("Attendee with this email already exists", HttpStatus.CONFLICT);
        }

        attendeeService.saveAttendee(attendee);
        return new ResponseEntity<>("Attendee added successfully", HttpStatus.CREATED);
    }

    // Get an attendee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Attendee> getAttendee(@PathVariable Long id) {
        Optional<Attendee> attendee = attendeeService.findById(id);
        if (attendee.isPresent()) {
            return new ResponseEntity<>(attendee.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get all attendees for a specific event
    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Attendee>> getAttendeesByEvent(@PathVariable Long eventId) {
        List<Attendee> attendees = attendeeService.findAttendeesByEvent(eventId);
        if (attendees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(attendees, HttpStatus.OK);
    }

    // Update attendee details
    @PutMapping("/{id}")
    public ResponseEntity<String> updateAttendee(@PathVariable Long id, @Valid @RequestBody Attendee attendee, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>("Invalid attendee data", HttpStatus.BAD_REQUEST);
        }

        Optional<Attendee> existingAttendee = attendeeService.findById(id);
        if (!existingAttendee.isPresent()) {
            return new ResponseEntity<>("Attendee not found", HttpStatus.NOT_FOUND);
        }

        attendee.setId(id); // Ensure the attendee ID is correct
        attendeeService.saveAttendee(attendee);
        return new ResponseEntity<>("Attendee updated successfully", HttpStatus.OK);
    }

    // Delete an attendee
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAttendee(@PathVariable Long id) {
        Optional<Attendee> attendee = attendeeService.findById(id);
        if (attendee.isPresent()) {
            attendeeService.deleteAttendee(id);
            return new ResponseEntity<>("Attendee deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Attendee not found", HttpStatus.NOT_FOUND);
        }
    }
}
