package com.jencruz.taskapplication.controller;

import com.jencruz.taskapplication.dto.TaskDTO;
import com.jencruz.taskapplication.exceptions.TaskNotFoundException;
import com.jencruz.taskapplication.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/task")
@Validated
public class TaskController {

    private final TaskService service;

    @Autowired
    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getTasks() {
        return new ResponseEntity<>(service.getTasks(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok(service.getTask(id));
    }

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@Valid @RequestBody TaskDTO taskDTO) {
        return new ResponseEntity<>(service.createTask(taskDTO), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable(value = "id") String id, @RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(service.updateTask(taskDTO, id));
    }

    @PutMapping(value = "/{id}/status")
    public ResponseEntity<TaskDTO> updateTaskStatus(@PathVariable(value = "id") String id,
            @Valid @RequestBody TaskDTO taskDTO) {
        return new ResponseEntity<>(service.updateTaskStatus(id, taskDTO.getStatus()), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable(value = "id") String id) {
        service.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/status/{status}")
    public ResponseEntity<List<TaskDTO>> findTaskByStatus(@PathVariable(value = "status") String status) {
        return new ResponseEntity<>(service.findByStatus(status), HttpStatus.OK);
    }
}
