package com.jencruz.taskapplication.service;

import com.jencruz.taskapplication.repository.TaskRepository;
import com.jencruz.taskapplication.service.TaskService;
import com.jencruz.taskapplication.dto.TaskDTO;
import com.jencruz.taskapplication.exceptions.TaskNotFoundException;
import com.jencruz.taskapplication.model.Task;
import com.jencruz.taskapplication.utils.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskServiceImpl(TaskRepository repository, TaskMapper taskMapper) {
        this.repository = repository;
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskDTO getTask(String id) {
        Optional<Task> task = repository.findById(id);
        if (task.isPresent()) {
            return taskMapper.mapToTaskDTO(task.get());
        } else {
            throw new TaskNotFoundException("No Task found!");
        }
    }

    @Override
    public List<TaskDTO> getTasks() {
        List<TaskDTO> taskDTOS = repository
                .findAll()
                .stream()
                .map(taskMapper::mapToTaskDTO)
                .collect(Collectors.toList());

        if (taskDTOS.isEmpty()) {
            throw new TaskNotFoundException("NO TASKS FOUND!");
        }

        return taskDTOS;
    }

    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {
        Task task = taskMapper.mapToTask(taskDTO);

        task.setTaskName(taskDTO.getTaskName());
        task.setDescription(task.getDescription());
        task.setStatus("PENDING");
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());

        return taskMapper.mapToTaskDTO(repository.save(task));
    }

    @Override
    public TaskDTO updateTask(TaskDTO taskDTO, String id) {
        Optional<Task> optionalTask = repository.findById(id);
        if (optionalTask.isPresent()) {

            Task task = taskMapper.mapToTask(taskDTO);
            task.setTaskName(taskDTO.getTaskName());
            task.setDescription(taskDTO.getDescription());
            task.setUpdatedAt(LocalDateTime.now());

            return taskMapper.mapToTaskDTO(repository.save(task));
        } else {
            throw new TaskNotFoundException("NO TASK FOUND!");
        }
    }

    @Override
    public TaskDTO updateTaskStatus(String id, String status) {
        Optional<Task> optionalTask = repository.findById(id);
        if (optionalTask.isEmpty()) {
            throw new TaskNotFoundException("NO TASK FOUND!");
        }
        Task task = optionalTask.get();
        task.setStatus(status);

        return taskMapper.mapToTaskDTO(repository.save(task));
    }

    @Override
    public void deleteTask(String id) {
        Task task = repository.findById(id).orElseThrow(() -> new TaskNotFoundException("No task found"));
        repository.deleteById(task.getId());
    }

    @Override
    public List<TaskDTO> findByStatus(String status) {
        return repository.findByStatus(status)
                .stream()
                .map(taskMapper::mapToTaskDTO)
                .toList();
    }
}
