package com.example.kanban.controller;

import com.example.kanban.model.KanbanModel;
import com.example.kanban.service.KanbanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class KanbanController {

    @Autowired
    private KanbanService KanbanService;

    @PostMapping
    public KanbanModel criarTask(@RequestBody KanbanModel kanbanmodel) {
        return KanbanService.createTask(kanbanmodel);
    }

    @GetMapping
    public List<KanbanModel> getTasks() {
        return KanbanService.getTasksByStatus("A Fazer");
    }

    @PutMapping("/{id}/move")
    public KanbanModel moveTask(@PathVariable Long id) {
        return KanbanService.moveTask(id);
    }

    @PutMapping("/{id}")
    public KanbanModel updateTask(@PathVariable Long id, @RequestBody KanbanModel taskDetails) {
        return KanbanService.updateTask(id, taskDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        KanbanService.deleteTask(id);
    }

    @GetMapping("/overdue")
    public List<KanbanModel> getOverdueTasks() {
        return KanbanService.getOverdueTasks();
    }
}
