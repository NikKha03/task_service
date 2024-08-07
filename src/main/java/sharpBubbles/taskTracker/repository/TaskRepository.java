package sharpBubbles.taskTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sharpBubbles.taskTracker.model.Task;
import sharpBubbles.taskTracker.model.TaskStatus;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    void deleteTaskByTaskId(Long taskId);

    Task findTaskByTaskId(Long taskId);

    Task findTaskByOwner(Long ownerId);

    List<Task> getAllTasksByOwner(Long owner);

    List<Task> getAllByOwnerAndTaskStatus(Long owner, TaskStatus status);
}
