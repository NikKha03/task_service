package sharpBubbles.taskTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sharpBubbles.taskTracker.model.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    void deleteTaskByTaskId(Long taskId);
    Task findTaskByTaskId(Long taskId);
    List<Task> getAllTasks(Long owner);
}
