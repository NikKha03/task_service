package sharpBubbles.taskTracker.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import sharpBubbles.taskTracker.model.Task;
import sharpBubbles.taskTracker.repository.TaskRepository;
import sharpBubbles.taskTracker.service.TaskService;

import java.util.List;


@Service
@AllArgsConstructor
@Primary
public class TaskServiceImpl implements TaskService {
    private final TaskRepository repository;

    @Override
    public List<Task> getAllTasks() {
        return repository.getAllTask();
    }

    @Override
    public Task createNewTask(Task task) {
        return repository.save(task);
    }


    @Override
    public void deleteTask(Task task) {
        repository.delete(task);
    }

    @Override
    public Task changeTask(Task task) {
        return repository.save(task);
    }


    public Task updateTask(Task task) {
        return repository.save(task);
    }
}
