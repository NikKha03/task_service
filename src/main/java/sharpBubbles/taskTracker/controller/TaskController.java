package sharpBubbles.taskTracker.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sharpBubbles.taskTracker.DTO.TaskRequest;
import sharpBubbles.taskTracker.model.Task;
import sharpBubbles.taskTracker.service.TaskService;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("task-tracker/tasks")
public class TaskController {

    private final TaskService service;

    @PostMapping("{owner}/create")
    public Task createNewTask(@RequestBody TaskRequest request, @PathVariable("owner") Long owner) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String header = request.getHeader();
        Long taskId = request.getTaskId();
        String comment = request.getComment();
        LocalDateTime dateTime = LocalDateTime.parse(request.getDateTimeOfTask(), formatter);
        Task newTask = new Task(taskId, header, comment, owner, dateTime, Task.Status.IN_PROGRESS);
        return service.createNewTask(newTask);
    }

    @Transactional
    @DeleteMapping("{owner}/delete/{taskId}")
    public void deleteTask(@PathVariable("taskId") Long taskId) {
        service.deleteTask(taskId);
    }

    @Transactional
    @PutMapping("{owner}/change/{taskId}")
    public Task changeTask(@RequestBody TaskRequest request, @PathVariable("owner") Long owner, @PathVariable("taskId") Long taskId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String header = request.getHeader();
        String comment = request.getComment();
        LocalDateTime dateTime = LocalDateTime.parse(request.getDateTimeOfTask(), formatter);
        Task newTask = new Task(taskId, header, comment, owner, dateTime, Task.Status.IN_PROGRESS);
        return service.changeTask(newTask);
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