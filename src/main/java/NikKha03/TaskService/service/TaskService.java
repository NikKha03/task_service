package NikKha03.TaskService.service;

import NikKha03.TaskService.DTO.TaskRequest;
import NikKha03.TaskService.model.Task;

import java.util.List;

public interface TaskService {

    /*
    List<Task> getTasksOnTheDay(Long owner, String category);
    List<Task> getTasksOnOtherDays(Long owner, String category);
     */

    Task findTaskById(Long id);

    List<Task> getByCategory(Long categoryId);

    List<Task> getAwaitingCompletionTasks(String implementer, Long categoryId);

    List<Task> getInProgressTasks(String implementer, Long categoryId);

    List<Task> getCompletedTask(String implementer, Long categoryId);

    List<Task> getTasksIncomplete(String implementer);

    List<Task> getTaskWithoutDateImpl(String implementer);

    Task createTask(String creator, TaskRequest request);

    Task changeTask(Long taskId, TaskRequest request);

    void deleteTask(Long taskId);

    Task setStatusOnCompleted(Long taskId);

    Task setStatusOnInProgress(Long taskId);

    Task setStatusOnAwaitingCompletion(Long taskId);

}
