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
@RequestMapping("/taskServiceApi/{ownerId}")
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/allTasks")
    public List<Task> getAllTasks(@PathVariable("ownerId") Long ownerId) {
        return taskService.getAllTasks(ownerId);
    }

    @GetMapping("/allCompletedTasks")
    public List<Task> getCompletedTask(@PathVariable("ownerId") Long ownerId) {
        return taskService.getCompletedTasks(ownerId);
    }

    @GetMapping("/allInProgressTasksWithDatePlannedImplementation")
    public List<Task> getInProgressTaskWithDatePlannedImplementation(@PathVariable("ownerId") Long ownerId) {
        return taskService.getInProgressTasksWithDatePlannedImplementation(ownerId);
    }

    @GetMapping("/allInProgressTasksWithoutDatePlannedImplementation")
    public List<Task> getInProgressTaskWithoutDatePlannedImplementation(@PathVariable("ownerId") Long ownerId) {
        return taskService.getInProgressTasksWithoutDatePlannedImplementation(ownerId);
    }

    @PostMapping("/createTask")
    public Task createTask(@PathVariable("ownerId") Long ownerId, @RequestBody TaskRequest request) {
        TaskBuilder taskBuilder = new TaskBuilder()
                .setTaskId(request.getTaskId())
                .setHeader(request.getHeader())
                .setComment(request.getComment())
                .setOwner(ownerId)
                .setTaskStatus(TaskStatus.IN_PROGRESS);

        if (request.getDatePlannedImplementation() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTimeOfTask = LocalDateTime.parse(request.getDatePlannedImplementation(), formatter);
            taskBuilder.setDatePlannedImplementation(dateTimeOfTask);
        }

        return taskService.createTask(taskBuilder.build());
    }

    @Transactional
    @PutMapping("/changeTask/{taskId}")
    public Task changeTask(@PathVariable("ownerId") Long ownerId, @PathVariable("taskId") Long taskId, @RequestBody TaskRequest request) {
        Task task = taskService.findTaskByTaskId(taskId);

        TaskBuilder taskBuilder = new TaskBuilder(task)
                .setHeader(request.getHeader())
                .setComment(request.getComment());

        if (request.getDatePlannedImplementation() != null && request.getDatePlannedImplementation().length() >= 10) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTimeOfTask = LocalDateTime.parse(request.getDatePlannedImplementation(), formatter);
            taskBuilder.setDatePlannedImplementation(dateTimeOfTask);
        } else {
            taskBuilder.setDatePlannedImplementation(null);
        }

        return taskService.changeTask(taskBuilder.build());
    }

    @Transactional
    @DeleteMapping("/deleteTask/{taskId}")
    public void deleteTask(@PathVariable("ownerId") Long ownerId, @PathVariable("taskId") Long taskId) {
        taskService.deleteTask(taskId);
    }

    @PutMapping("/changeTaskStatusOnCompleted/{taskId}")
    public Task changeTaskStatus(@PathVariable("ownerId") Long ownerId, @PathVariable("taskId") Long taskId) {
        Task task = taskService.findTaskByTaskId(taskId);

        LocalDateTime time = LocalDateTime.now();
        task.setExecutionDate(time);
        task.setTaskStatus(TaskStatus.COMPLETED);

        return taskService.changeTask(task);
    }
}