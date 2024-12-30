package com.spring.ems.service;



import com.spring.ems.entity.Task;
import com.spring.ems.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    // Create a Task
    public Task createTask(Task task) {
        return taskRepository.save(task);  // Save and return the created task
    }

    // Get Tasks for an Event
    public List<Task> getTasksForEvent(Long eventId) {
        return taskRepository.findByEventId(eventId);  // Fetch tasks based on event ID
    }

    // Update Task Status
    public Task updateTaskStatus(Long taskId, String status) {
        Task task = taskRepository.findById(taskId).orElse(null);  // Fetch the task by ID
        if (task != null) {
            task.setStatus(status);  // Update task status
            return taskRepository.save(task);  // Save and return the updated task
        }
        return null;  // Return null if task not found
    }
}
