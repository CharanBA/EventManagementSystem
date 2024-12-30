package com.spring.ems.dto;

import java.util.Date;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class EventDto {

    private Long id; // Optional: For updating or fetching existing events.

    @NotBlank(message = "Event name cannot be blank")
    @Size(max = 100, message = "Event name cannot exceed 100 characters")
    private String name;

    @NotBlank(message = "Event description cannot be blank")
    @Size(max = 500, message = "Event description cannot exceed 500 characters")
    private String description;

    @NotBlank(message = "Location cannot be blank")
    @Size(max = 200, message = "Location cannot exceed 200 characters")
    private String location;

    @NotNull(message = "Event date is required")
    @FutureOrPresent(message = "Event date cannot be in the past")
    private Date date;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
