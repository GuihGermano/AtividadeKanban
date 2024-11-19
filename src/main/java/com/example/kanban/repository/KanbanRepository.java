package com.example.kanban.repository;

import com.example.kanban.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface KanbanRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(String status);
    List<Task> findByPriority(String priority);
    List<Task> findByDueDateBeforeAndStatusNot(String status, LocalDateTime dueDate);
}
