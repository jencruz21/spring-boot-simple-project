package com.jencruz.taskapplication.tasks;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;
    private final TaskMapper taskMapper;

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
                .toList();

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
}
