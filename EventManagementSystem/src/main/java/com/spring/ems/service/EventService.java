package com.spring.ems.service;

import com.spring.ems.dto.EventRequestDto;
import com.spring.ems.entity.Event;
import com.spring.ems.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    // Fetch all events
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Create Event
    public Event createEvent(EventRequestDto eventRequestDto) {
        Event event = new Event();
        event.setName(eventRequestDto.getName());
        event.setDescription(eventRequestDto.getDescription());
        event.setLocation(eventRequestDto.getLocation());
        event.setDate(eventRequestDto.getDate());
        return eventRepository.save(event); // Save event in the database
    }

    // Update Event
    public Event updateEvent(Long eventId, EventRequestDto eventRequestDto) {
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            event.setName(eventRequestDto.getName());
            event.setDescription(eventRequestDto.getDescription());
            event.setLocation(eventRequestDto.getLocation());
            event.setDate(eventRequestDto.getDate());
            return eventRepository.save(event); // Save updated event
        }
        return null; // Return null if event not found
    }
}
