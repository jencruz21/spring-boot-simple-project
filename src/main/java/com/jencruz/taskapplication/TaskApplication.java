package com.jencruz.taskapplication;

import com.jencruz.taskapplication.repository.TaskRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class TaskApplication {

    TaskRepository repository;

    public TaskApplication(TaskRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);
    }
    
    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Manila"));
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext context) {
//        return args -> {
//            System.out.println("||=====|| CREATING TASKS ||=====||");
//            Task task = new Task();
//            task.setTaskName("Test 2");
//            task.setDescription("Test API 2");
//            task.setStatus("PENDING");
//            task.setUpdatedAt(LocalDateTime.now());
//            task.setCreatedAt(LocalDateTime.now());
//
//            Task task2 = new Task();
//            task2.setTaskName("Test 3");
//            task2.setDescription("Test API 3");
//            task2.setStatus("PENDING");
//            task2.setUpdatedAt(LocalDateTime.now());
//            task2.setCreatedAt(LocalDateTime.now());
//
//            repository.save(task);
//            repository.save(task2);
//        };
//    }
}
