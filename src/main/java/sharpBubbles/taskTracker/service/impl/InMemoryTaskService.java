package sharpBubbles.taskTracker.service.impl;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sharpBubbles.taskTracker.DAO.InMemoryTaskDAO;
import sharpBubbles.taskTracker.model.Task;
import sharpBubbles.taskTracker.service.TaskService;

import java.util.List;

@Service
@AllArgsConstructor
public class InMemoryTaskService implements TaskService {

    private final InMemoryTaskDAO repository;

    @Override
    public List<Task> getInProgressTasksWithDatePlannedImplementation(Long owner) {
        return List.of();
    }

    @Override
    public List<Task> getCompletedTasks(Long owner) {
        return List.of();
    }

    @Override
    public List<Task> getInProgressTasksWithoutDatePlannedImplementation(Long owner) {
        return List.of();
    }

    @Override
    public Task createTask(Task task) {
        return repository.creatTask(task);
    }

    @Override
    public void deleteTask(Long taskId) {
        repository.deleteTask(taskId);
    }

    @Override
    public Task changeTask(Task task) {
        return repository.changeTask(task);
    }

    @Override
    public List<Task> getAllTasks(Long owner) {
        return repository.getAllTasks(owner);
    }

    public Task findTaskByTaskId(Long id) {
        return repository.findTaskByTaskId(id);
    }

}
