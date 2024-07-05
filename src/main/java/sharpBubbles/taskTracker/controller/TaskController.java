package sharpBubbles.taskTracker.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sharpBubbles.taskTracker.model.Task;
import sharpBubbles.taskTracker.service.TaskService;
import java.util.List;

@RestController
@AllArgsConstructor
public class TaskController {

    private final TaskService service;

    @PostMapping
    public Task createNewTask(Task task) {
        return service.createNewTask(task);
    }

    @DeleteMapping
    public void deleteTask(Task task) {
        service.deleteTask(task);
    }

    @PutMapping
    public Task changeTask(@RequestBody Task task) {
        return service.changeTask(task);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return service.getAllTasks();
    }
}