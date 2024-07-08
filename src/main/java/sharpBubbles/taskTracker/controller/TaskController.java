package sharpBubbles.taskTracker.controller;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sharpBubbles.taskTracker.DTO.TaskRequest;
import sharpBubbles.taskTracker.model.Task;
import sharpBubbles.taskTracker.service.TaskService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("task-tracker/tasks")
public class TaskController {

    private final TaskService service;

    @PostMapping("{owner}/create")
    public Task createNewTask(@RequestBody TaskRequest request, @PathVariable("owner") Long owner) {
        String header = request.getHeader();
        Long taskId = request.getTaskId();
        String comment = request.getComment();
        LocalDateTime dateOfTask = request.getDateTimeOfTask();
        Task newTask = new Task(taskId, header, comment, owner, dateOfTask, Task.Status.IN_PROGRESS);
        return service.createNewTask(newTask);
    }

    @Transactional
    @DeleteMapping("{owner}/delete/{taskId}")
    public void deleteTask(@PathVariable("taskId") Long taskId) {
        service.deleteTask(taskId);
    }

    @PutMapping("{owner}/change/{taskId}")
    public Task changeTask(@RequestBody Task task) {
        return service.changeTask(task);
    }

    @GetMapping("{owner}")
    public List<Task> getAllTasks(@PathVariable("owner") Long owner) {
        return service.getAllTasks(owner);
    }

    @GetMapping("{owner}/{taskId}")
    public Task getTaskByTaskId(@PathVariable("taskId") Long taskId) {
        return service.findTaskByTaskId(taskId);
    }
}