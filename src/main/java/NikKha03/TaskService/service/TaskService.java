package NikKha03.TaskService.service;

import NikKha03.TaskService.DTO.TaskRequest;
import NikKha03.TaskService.model.Task;

import java.util.List;

public interface TaskService {

    /*
    List<Task> getCompletedTasks(Long owner, String category);

    List<Task> getTasksOnTheDay(Long owner, String category);

    List<Task> getTasksOnOtherDays(Long owner, String category);

    List<Task> getTasksIncomplete(Long owner, String category);

    List<Task> getInProgressTasksWithoutDatePlannedImplementation(Long owner, String category);
     */

    List<Task> getByCategory(Long categoryId);

    Task findTaskById(Long id);

    Task createTask(String owner, TaskRequest request);

    Task changeTask(Long taskId, TaskRequest request);

    void deleteTask(Long taskId);

    Task setStatusOnCompleted(Long taskId);

    Task setStatusOnInProgress(Long taskId);

    Task setStatusOnAwaitingCompletion(Long taskId);

}
