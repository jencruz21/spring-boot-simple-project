package com.jencruz.taskapplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jencruz.taskapplication.service.TaskService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = TaskController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(value = MockitoExtension.class)
public class TaskControllerTests {

    @MockBean
    private TaskService taskService;
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @Autowired
    public TaskControllerTests(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }
}
