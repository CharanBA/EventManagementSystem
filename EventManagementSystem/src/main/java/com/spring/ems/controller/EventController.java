package com.spring.ems.controller;

import com.spring.ems.dto.EventRequestDto;
import com.spring.ems.dto.EventResponseDto;
import com.spring.ems.entity.Event;
import com.spring.ems.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    // Create Event
    @PostMapping
    public ResponseEntity<EventResponseDto> createEvent(@RequestBody EventRequestDto eventRequestDto) {
        Event createdEvent = eventService.createEvent(eventRequestDto); // Create event using DTO
        EventResponseDto responseDto = mapToResponseDto(createdEvent);  // Map entity to response DTO
        return ResponseEntity.status(201).body(responseDto);            // Return response DTO
    }

    // Get All Events
    @GetMapping
    public ResponseEntity<List<EventResponseDto>> getAllEvents() {
        List<Event> events = eventService.getAllEvents(); // Fetch all events
        List<EventResponseDto> responseDtos = events.stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());            // Map entities to response DTOs
        return ResponseEntity.ok(responseDtos);          // Return response DTO list
    }

    // Update Event
    @PutMapping("/{eventId}")
    public ResponseEntity<EventResponseDto> updateEvent(@PathVariable Long eventId, @RequestBody EventRequestDto eventRequestDto) {
        Event updatedEvent = eventService.updateEvent(eventId, eventRequestDto); // Update event using DTO
        if (updatedEvent == null) {
            return ResponseEntity.notFound().build(); // Return 404 if event not found
        }
        EventResponseDto responseDto = mapToResponseDto(updatedEvent); // Map entity to response DTO
        return ResponseEntity.ok(responseDto);                        // Return response DTO
    }

    // Map Entity to Response DTO
    private EventResponseDto mapToResponseDto(Event event) {
        EventResponseDto dto = new EventResponseDto();
        dto.setId(event.getId());
        dto.setName(event.getName());
        dto.setDescription(event.getDescription());
        dto.setLocation(event.getLocation());
        dto.setDate(event.getDate());
        return dto;
    }
}
