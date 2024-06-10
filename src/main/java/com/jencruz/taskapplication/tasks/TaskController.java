package com.jencruz.taskapplication.tasks;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/task")
@Validated
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getTasks() {
        try {
            return new ResponseEntity<>(service.getTasks(), HttpStatus.OK);
        } catch (TaskNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable(value = "id") String id) {
        try {
            return ResponseEntity.ok(service.getTask(id));
        } catch (TaskNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@Valid @RequestBody TaskDTO taskDTO) {
        return new ResponseEntity<>(service.createTask(taskDTO), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable(value = "id") String id, @RequestBody TaskDTO taskDTO) {
        try {
            return ResponseEntity.ok(service.updateTask(taskDTO, id));
        } catch (TaskNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}/status")
    public ResponseEntity<TaskDTO> updateTaskStatus(@PathVariable(value = "id") String id, @Valid @RequestBody TaskDTO taskDTO) {
        try {
            return new ResponseEntity<>(service.updateTaskStatus(id, taskDTO.getStatus()), HttpStatus.OK);
        } catch (TaskNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable(value = "id") String id) {
        try {
            service.deleteTask(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (TaskNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
