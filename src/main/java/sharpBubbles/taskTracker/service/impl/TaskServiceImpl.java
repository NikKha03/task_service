package sharpBubbles.taskTracker.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import sharpBubbles.taskTracker.model.Task;
import sharpBubbles.taskTracker.model.TaskStatus;
import sharpBubbles.taskTracker.repository.TaskRepository;
import sharpBubbles.taskTracker.service.TaskService;

import java.util.Comparator;
import java.util.List;


@Service
@AllArgsConstructor
@Primary
public class TaskServiceImpl implements TaskService {
    private final TaskRepository repository;

    @Override
    public List<Task> getAllTasks(Long owner) {
        return repository.getAllTasksByOwner(owner);
    }

    @Override
    public List<Task> getCompletedTasks(Long owner) {
        return repository.getAllByOwnerAndTaskStatus(owner, TaskStatus.COMPLETED).stream()
                .sorted(Comparator.comparing(Task::getExecutionDate))
                .toList();
    }

    @Override
    public List<Task> getInProgressTasksWithDatePlannedImplementation(Long owner) {
        List<Task> tasks = repository.getAllByOwnerAndTaskStatus(owner, TaskStatus.IN_PROGRESS);

        return tasks.stream()
                .filter(task -> task.getDatePlannedImplementation() != null)
                .sorted(Comparator.comparing(Task::getCreationDate))
                .toList();
    }

    @Override
    public List<Task> getInProgressTasksWithoutDatePlannedImplementation(Long owner) {
        List<Task> tasks = repository.getAllByOwnerAndTaskStatus(owner, TaskStatus.IN_PROGRESS);

        return tasks.stream()
                .filter(task -> task.getDatePlannedImplementation() == null)
                .sorted(Comparator.comparing(Task::getCreationDate))
                .toList();
    }

    @Override
    public Task createTask(Task task) {
        return repository.save(task);
    }

    @Override
    public void deleteTask(Long taskId) {
        repository.deleteTaskByTaskId(taskId);
    }

    @Override
    public Task changeTask(Task task) {
        return repository.save(task);
    }

    @Transactional
    public Task updateTask(Task task) {
        return repository.save(task);
    }

    public Task findTaskByTaskId(Long id) {
        return repository.findTaskByTaskId(id);
    }
}
