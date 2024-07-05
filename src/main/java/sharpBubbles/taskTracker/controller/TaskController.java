package sharpBubbles.taskTracker.controller;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sharpBubbles.taskTracker.model.Task;
import sharpBubbles.taskTracker.service.TaskService;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService service;

    @PostMapping("create_task")
    public Task createNewTask(@RequestBody Task task) {
        return service.createNewTask(task);
    }

    @Transactional
    @DeleteMapping("delete_task/{taskId}")
    public void deleteTask(@PathVariable("taskId") Long taskId) {
        service.deleteTask(taskId);
    }

    @PutMapping("change_task")
    public Task changeTask(@RequestBody Task task) {
        return service.changeTask(task);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return service.getAllTasks();
    }
}