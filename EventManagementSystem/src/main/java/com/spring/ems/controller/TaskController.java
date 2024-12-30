package com.spring.ems.controller;

import com.spring.ems.entity.Task;
import com.spring.ems.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Create a Task
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.status(201).body(createdTask);  // Return created task with status 201
    }

    // Get Tasks for an Event
    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Task>> getTasksForEvent(@PathVariable Long eventId) {
        List<Task> tasks = taskService.getTasksForEvent(eventId);
        if (tasks.isEmpty()) {
            return ResponseEntity.noContent().build();  // Return 204 if no tasks found
        }
        return ResponseEntity.ok(tasks);  // Return tasks with status 200
    }

    // Update Task Status
    @PutMapping("/{taskId}/status")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Long taskId, @RequestParam String status) {
        Task updatedTask = taskService.updateTaskStatus(taskId, status);
        if (updatedTask == null) {
            return ResponseEntity.notFound().build();  // Return 404 if task not found
        }
        return ResponseEntity.ok(updatedTask);  // Return updated task with status 200
    }
}

