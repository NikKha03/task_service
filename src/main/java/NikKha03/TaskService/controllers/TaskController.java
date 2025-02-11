package NikKha03.TaskService.controllers;

import NikKha03.TaskService.DTO.TaskRequest;
import NikKha03.TaskService.model.Task;
import NikKha03.TaskService.service.TaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "task_controller")
@RestController
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /*
    Есть задачи, которые запрашиваю по userId,
    а есть те, которые можно запросить и по userId и по categoryId!!!
    Поэтому, один из параметров может быть null!
    */

    // задачи, которые нужно сделать
    @GetMapping("/awaitingCompletionTasks")
    public List<Task> getAwaitingCompletionTasks(@RequestParam String userId, @RequestParam Long categoryId) {
        return null;
    }

    // задачи, которые в работе
    @GetMapping("/inProgressTasks")
    public List<Task> getInProgressTasks(@RequestParam String userId, @RequestParam Long categoryId) {
        return null;
    }

    // задачи, которые выполнены
    @GetMapping("/completedTasks")
    public List<Task> getCompletedTask(@RequestParam String userId, @RequestParam Long categoryId) {
        return null;
    }

    // задачи, которые не были выполнены
    @GetMapping("/incompleteTasks")
    public List<Task> getTasksIncomplete(@RequestParam String userId) {
        return null;
    }

    // задачи без даты выполнения
    @GetMapping("/withoutDateImplTasks")
    public List<Task> getTaskWithoutDateImpl(@RequestParam String userId) {
        return null;
    }

    @PostMapping("/createTask/{owner}")
    public Task createTask(@PathVariable("owner") String owner, @RequestBody TaskRequest request) {
        return taskService.createTask(owner, request);
    }

    @Transactional
    @PutMapping("/changeTask/{taskId}")
    public Task changeTask(@PathVariable("taskId") Long taskId, @RequestBody TaskRequest request) {
        return taskService.changeTask(taskId, request);
    }

    @Transactional
    @DeleteMapping("/deleteTask/{taskId}")
    public void deleteTask(@PathVariable("taskId") Long taskId) {
        taskService.deleteTask(taskId);
    }

    @PutMapping("/setStatusOnCompleted/{taskId}")
    public Task setStatusOnCompleted(@PathVariable("taskId") Long taskId) {
        return taskService.setStatusOnCompleted(taskId);
    }

    @PutMapping("/setStatusOnInProgress/{taskId}")
    public Task setStatusOnInProgress(@PathVariable("taskId") Long taskId) {
        return taskService.setStatusOnInProgress(taskId);
    }

    @PutMapping("/setStatusOnAwaitingCompletion/{taskId}")
    public Task setStatusOnAwaitingCompletion(@PathVariable("taskId") Long taskId) {
        return taskService.setStatusOnAwaitingCompletion(taskId);
    }

}