package com.jencruz.taskapplication.repository;

import com.jencruz.taskapplication.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, String> {
    List<Task> findByStatus(String status);
}
