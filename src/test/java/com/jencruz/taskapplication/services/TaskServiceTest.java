package com.jencruz.taskapplication.services;

import com.jencruz.taskapplication.dto.TaskDTO;
import com.jencruz.taskapplication.model.Task;
import com.jencruz.taskapplication.repository.TaskRepository;
import com.jencruz.taskapplication.service.TaskServiceImpl;
import com.jencruz.taskapplication.utils.TaskMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskServiceImpl taskService;

    private Task task;
    private Task task2;
    private TaskDTO taskDTO;
    private TaskDTO taskDTO2;

    @BeforeEach
    public void init() {
        task = Task.builder()
                .taskName("demo_task")
                .status("COMPLETED")
                .description("this is a demo task")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        task2 = Task.builder()
                .taskName("demo_task2")
                .status("COMPLETED")
                .description("this is a demo task2")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        taskDTO = TaskDTO.builder()
                .taskName(task.getTaskName())
                .status(task.getStatus())
                .description(task.getDescription())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .build();

        taskDTO2 = new TaskDTO(task2.getId(), task2.getTaskName(), task2.getStatus(), task2.getDescription(), task2.getCreatedAt(), task2.getUpdatedAt());
    }

    @Test
    public void TaskService_GetTasks_ReturnsTasks() {

        List<Task> tasks = Arrays.asList(task, task2);

        when(taskRepository.findAll()).thenReturn(tasks);
        when(taskMapper.mapToTaskDTO(task)).thenReturn(taskDTO);
        when(taskMapper.mapToTaskDTO(task2)).thenReturn(taskDTO2);

        List<TaskDTO> result = taskService.getTasks();

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result.get(0).getTaskName()).isEqualTo("demo_task");
        Assertions.assertThat(result.get(1).getTaskName()).isEqualTo("demo_task2");
    }

    @Test
    public void TaskService_CreateTask_ReturnsTask() {

        when(taskMapper.mapToTask(taskDTO)).thenReturn(task);
        when(taskRepository.save(Mockito.any(Task.class))).thenReturn(task);
        when(taskMapper.mapToTaskDTO(task)).thenReturn(taskDTO);

        TaskDTO result = taskService.createTask(taskDTO);

        Assertions.assertThat(result).isNotNull();
    }

    @Test
    public void TaskService_GetTask_ReturnsTask() {
        String taskId = "1";
        Optional<Task> optionalTask = Optional.of(task);
        when(taskRepository.findById(taskId)).thenReturn(optionalTask);
        when(taskMapper.mapToTaskDTO(optionalTask.get())).thenReturn(taskDTO);

        TaskDTO result = taskService.getTask(taskId);

        Assertions.assertThat(result).isNotNull();
    }

    @Test
    public void TaskService_UpdateTask_ReturnsUpdatedTask() {
        String taskId = "1";
        Optional<Task> optionalTask = Optional.of(task);

        when(taskMapper.mapToTask(taskDTO)).thenReturn(task);
        when(taskRepository.findById(taskId)).thenReturn(optionalTask);
        when(taskRepository.save(Mockito.any(Task.class))).thenReturn(task);
        when(taskMapper.mapToTaskDTO(Mockito.any(Task.class))).thenReturn(taskDTO);

        TaskDTO result = taskService.updateTask(taskDTO, taskId);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getTaskName()).isEqualTo(taskDTO.getTaskName());
        Assertions.assertThat(result.getStatus()).isEqualTo(taskDTO.getStatus());
        Assertions.assertThat(result.getDescription()).isEqualTo(taskDTO.getDescription());
        Assertions.assertThat(result.getUpdatedAt()).isEqualTo(taskDTO.getUpdatedAt());

        // Verify that the taskRepository and taskMapper are called as expected
        Mockito.verify(taskRepository).findById(taskId);
        Mockito.verify(taskRepository).save(Mockito.any(Task.class));
        Mockito.verify(taskMapper).mapToTaskDTO(Mockito.any(Task.class));
    }
}
