package com.jencruz.taskapplication.repository;

import com.jencruz.taskapplication.model.Task;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TaskRepositoryTests {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskRepositoryTests(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Test
    public void TaskRepository_SaveTask_ReturnsSavedTask() {
        Task task = Task.builder()
                .taskName("demo_task")
                .status("PENDING")
                .description("this is a demo task")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Task result = taskRepository.save(task);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isGreaterThan("0");
    }

    @Test
    public void TaskRepository_FindAll_ReturnsAllTask() {
        Task task1 = Task.builder()
                .taskName("demo_task1")
                .status("PENDING")
                .description("this is a demo task")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        Task task2 = Task.builder()
                .taskName("demo_task2")
                .status("FINISHED")
                .description("this is a demo task")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        taskRepository.save(task1);
        taskRepository.save(task2);

        List<Task> tasks = taskRepository.findAll();

        Assertions.assertThat(tasks).isNotNull();
        Assertions.assertThat(tasks.size()).isEqualTo(2);
    }

    @Test
    public void TaskRepository_FindById_ReturnsTask() {
        Task task1 = Task.builder()
                .taskName("demo_task")
                .status("PENDING")
                .description("this is a demo task")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Task task2 = taskRepository.save(task1);

        Task result = taskRepository.findById(task2.getId()).get();

        Assertions.assertThat(task2).isNotNull();
        Assertions.assertThat(result).isNotNull();
    }

    @Test
    public void TaskRepository_UpdateTask_ReturnsUpdatedTask() {
        Task task1 = Task.builder()
                .taskName("demo_task")
                .status("PENDING")
                .description("this is a demo task")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        Task task2 = taskRepository.save(task1);

        Task task3 = taskRepository.findById(task2.getId()).get();
        task3.setTaskName("updated task");
        task3.setDescription("updated description");
        task3.setStatus("COMPLETED");
        Task result = taskRepository.save(task3);

        Assertions.assertThat(task3).isNotNull();
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getTaskName()).isEqualTo("updated task");
        Assertions.assertThat(result.getDescription()).isEqualTo("updated description");
        Assertions.assertThat(result.getStatus()).isEqualTo("COMPLETED");
    }

    @Test
    public void TaskRepository_DeleteById_ReturnsTask() {
        Task task1 = Task.builder()
                .taskName("demo_task")
                .status("PENDING")
                .description("this is a demo task")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Task task2 = taskRepository.save(task1);

        taskRepository.deleteById(task2.getId());

        List<Task> tasks = taskRepository.findAll();

        Assertions.assertThat(task2).isNotNull();
        Assertions.assertThat(tasks.size()).isLessThan(1);
    }
}
