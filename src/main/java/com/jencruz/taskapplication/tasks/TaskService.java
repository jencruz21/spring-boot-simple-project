package com.jencruz.taskapplication.tasks;

import java.util.List;

public interface TaskService {

    TaskDTO getTask(String id);
    List<TaskDTO> getTasks();

    TaskDTO createTask(TaskDTO taskDto);

    TaskDTO updateTask(TaskDTO taskDto, String id);

    TaskDTO updateTaskStatus(String id, String taskStatus);

    void deleteTask(String id);
}
