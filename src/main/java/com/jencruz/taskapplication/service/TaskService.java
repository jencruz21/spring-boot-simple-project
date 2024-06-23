package com.jencruz.taskapplication.service;

import com.jencruz.taskapplication.dto.TaskDTO;

import java.util.List;

public interface TaskService {

    TaskDTO getTask(String id);
    List<TaskDTO> getTasks();

    TaskDTO createTask(TaskDTO taskDto);

    TaskDTO updateTask(TaskDTO taskDto, String id);

    TaskDTO updateTaskStatus(String id, String taskStatus);

    List<TaskDTO> findByStatus(String status);

    void deleteTask(String id);
}
