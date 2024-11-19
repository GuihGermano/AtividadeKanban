package com.example.kanban.service;

import com.example.kanban.model.KanbanModel;
import com.example.kanban.repository.KanbanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class KanbanService {

    @Autowired
    private KanbanRepository kanbanrepository;

    public KanbanModel createTask(KanbanModel task) {
        task.setStatus("A Fazer"); // Sempre cria na coluna "A Fazer"
        return KanbanRepository.save(KanbanModel);
    }

    public List<KanbanModel> getTasksByStatus(String status) {
        return KanbanRepository.findByStatus(status);
    }

    public KanbanModel updateTask(Long id, KanbanModel taskDetails) {
        KanbanModel kanbanModel = KanbanRepository.findById(id).orElseThrow();
        kanbanModel.setTitle(taskDetails.getTitle());
        kanbanModel.setDescription(taskDetails.getDescription());
        kanbanModel.setPriority(taskDetails.getPriority());
        return kanbanrepository.save(kanbanModel);
    }

    public void deleteTask(Long id) {
        KanbanRepository.deleteById(id);
    }

    public Task moveTask(Long id) {
        Task task = KanbanRepository.findById(id).orElseThrow();
        if ("A Fazer".equals(task.getStatus())) {
            KanbanModel.setStatus("Em Progresso");
        } else if ("Em Progresso".equals(task.getStatus())) {
            KanbanModel.setStatus("Concluido");
        }
        return kanbanrepository.save(task);
    }

    public List<Task> getOverdueTasks() {
        return kanbanrepository.findByDueDateBeforeAndStatusNot("Concluido", LocalDateTime.now());
    }
}
