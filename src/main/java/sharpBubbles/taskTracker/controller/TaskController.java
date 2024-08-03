package sharpBubbles.taskTracker.controller;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sharpBubbles.taskTracker.DTO.TaskRequest;
import sharpBubbles.taskTracker.model.Task;
import sharpBubbles.taskTracker.model.TaskBuilder;
import sharpBubbles.taskTracker.model.TaskStatus;
import sharpBubbles.taskTracker.service.TaskService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("task-tracker/tasks/{owner}")
public class TaskController {

    private final TaskService service;

    @GetMapping()
    public List<Task> getAllTasks(@PathVariable("owner") Long owner) {
        return service.getAllTasks(owner);
    }

    @GetMapping("/{taskId}")
    public Task getTaskByTaskId(@PathVariable("taskId") Long taskId) {
        return service.findTaskByTaskId(taskId);
    }

    @PostMapping("/create")
    public Task createTask(@PathVariable("owner") Long owner, @RequestBody TaskRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTimeOfTask = LocalDateTime.parse(request.getDateTimeOfTask(), formatter);

        Task task = new TaskBuilder()
                .setTaskId(request.getTaskId())
                .setHeader(request.getHeader())
                .setComment(request.getComment())
                .setOwner(owner)
                .setDateTimeOfTask(dateTimeOfTask)
                .setTaskStatus(TaskStatus.IN_PROGRESS)
                .build();

        return service.createTask(task);
    }

    @Transactional
    @PutMapping("/change/{taskId}")
    public Task changeTask(@PathVariable("owner") Long owner, @PathVariable("taskId") Long taskId, @RequestBody TaskRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTimeOfTask = LocalDateTime.parse(request.getDateTimeOfTask(), formatter);

        Task task = service.findTaskByTaskId(taskId);

        Task neweTask = new TaskBuilder(task)
                .setHeader(request.getHeader())
                .setComment(request.getComment())
                .setDateTimeOfTask(dateTimeOfTask)
                .build();

        return service.changeTask(neweTask);
    }

    @Transactional
    @DeleteMapping("/delete/{taskId}")
    public void deleteTask(@PathVariable("taskId") Long taskId) {
        service.deleteTask(taskId);
    }
}