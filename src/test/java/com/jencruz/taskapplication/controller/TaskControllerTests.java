package com.jencruz.taskapplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jencruz.taskapplication.dto.TaskDTO;
import com.jencruz.taskapplication.model.Task;
import com.jencruz.taskapplication.service.TaskService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(value = MockitoExtension.class)
public class TaskControllerTests {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private ObjectMapper mapper;

    private Task task;
    private Task task2;
    private TaskDTO taskDTO;
    private TaskDTO taskDTO2;

    @BeforeEach
    public void setUp() {
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
    public void TaskController_CreateTask_ReturnCreated() {
        Mockito.when(taskService.createTask(Mockito.any(TaskDTO.class))).thenReturn(taskDTO);

        ResponseEntity<? extends TaskDTO> response = taskController.createTask(taskDTO);

        Assertions.assertNotNull(response);
    }
}
