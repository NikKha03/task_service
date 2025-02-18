package NikKha03.TaskService.service;

import NikKha03.TaskService.DTO.TaskRequest;
import NikKha03.TaskService.model.Task;
import NikKha03.TaskService.model.TaskStatus;

import java.util.List;

public interface TaskService {

    /*
    List<Task> getTasksOnTheDay(Long owner, String category);
    List<Task> getTasksOnOtherDays(Long owner, String category);
     */

    Task findTaskById(Long id);

    List<Task> getByCategory(Long categoryId);

    List<Task> getTasksByStatus(String implementer, String status);

    List<Task> getInProgressTasks(String implementer);

    List<Task> getTasksIncomplete(String implementer);

    List<Task> getTaskWithoutDateImpl(String implementer);

    Task createTask(String creator, TaskRequest request);

    Task changeTask(Long taskId, TaskRequest request);

    void deleteTask(Long taskId);

    Task setStatus(Long taskId, TaskStatus status, boolean resetExecutionDate);

}
