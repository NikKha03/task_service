package NikKha03.TaskService.service;

import NikKha03.TaskService.model.Task;

import java.util.List;

public interface TaskService {

    List<Task> getCompletedTasks(Long owner, String category);

    List<Task> getTasksOnTheDay(Long owner, String category);

    List<Task> getTasksOnOtherDays(Long owner, String category);

    List<Task> getTasksIncomplete(Long owner, String category);

    List<Task> getInProgressTasksWithoutDatePlannedImplementation(Long owner, String category);

    Task createTask(Task task);

    void deleteTask(Long taskId);

    Task changeTask(Task task);

    Task findTaskByTaskId(Long id);

}
