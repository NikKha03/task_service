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
    public List<Task> getTasksIncomplete(Long owner, String category) {
        return List.of();
    }

    @Override
    public List<Task> getTasksOnTheDay(Long owner, String category) {
        return List.of();
    }

    @Override
    public List<Task> getTasksOnOtherDays(Long owner, String category) {
        return List.of();
    }

    @Override
    public List<Task> getCompletedTasks(Long owner, String category) {
        return List.of();
    }

    @Override
    public List<Task> getInProgressTasksWithoutDatePlannedImplementation(Long owner, String category) {
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

    public Task findTaskByTaskId(Long id) {
        return repository.findTaskByTaskId(id);
    }

}
