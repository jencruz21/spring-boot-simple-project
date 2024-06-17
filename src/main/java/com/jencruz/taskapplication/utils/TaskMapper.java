package com.jencruz.taskapplication.utils;

import com.jencruz.taskapplication.dto.TaskDTO;
import com.jencruz.taskapplication.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public Task mapToTask(TaskDTO taskDTO) {
        return new Task(
                taskDTO.getId(),
                taskDTO.getTaskName(),
                taskDTO.getDescription(),
                taskDTO.getStatus(),
                taskDTO.getCreatedAt(),
                taskDTO.getUpdatedAt()
        );
    }

    public TaskDTO mapToTaskDTO(Task task) {
        return new TaskDTO(
                task.getId(),
                task.getTaskName(),
                task.getDescription(),
                task.getStatus(),
                task.getCreatedAt(),
                task.getUpdatedAt()
        );
    }
}
