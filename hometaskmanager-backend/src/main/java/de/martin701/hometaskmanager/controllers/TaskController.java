package de.martin701.hometaskmanager.controllers;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import de.martin701.hometaskmanager.entities.Task;
import de.martin701.hometaskmanager.exceptions.ResourceNotFoundException;
import de.martin701.hometaskmanager.services.TaskService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return taskService.findAll();
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable(value = "id") Long taskId)
            throws ResourceNotFoundException {
        Task task = taskService.findById(taskId);
        if (task == null)
            throw new ResourceNotFoundException("Task not found for this id :: " + taskId);
        return ResponseEntity.ok().body(task);
    }

    @PostMapping("/task")
    public Task createTask(@Valid @RequestBody Task task) {
        return taskService.save(task);
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<?> updateTask(@PathVariable(value = "id") Long taskId,
                                        @Valid @RequestBody Task newTask) throws ResourceNotFoundException {

        Task oldTask = taskService.findById(taskId);
        if (oldTask == null)
            throw new ResourceNotFoundException("Task not found for this id :: " + taskId);

        List<String> problems = taskService.checkValidity(newTask);
        if (!problems.isEmpty()) {
            String errorMessage = String.join(", ", problems);
            return ResponseEntity.badRequest().body(errorMessage);
        }

        oldTask = taskService.updateTask(oldTask, newTask);
        final Task updatedTask = taskService.save(oldTask);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/task/{id}")
    public Map<String, Boolean> deleteTask(@PathVariable(value = "id") Long taskId)
            throws ResourceNotFoundException {
        Task task = taskService.findById(taskId);
//                .orElseThrow(() -> new ResourceNotFoundException("Task not found for this id :: " + taskId));

        taskService.delete(task);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
