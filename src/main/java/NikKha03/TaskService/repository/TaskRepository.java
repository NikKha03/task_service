package NikKha03.TaskService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import NikKha03.TaskService.model.Task;
import NikKha03.TaskService.model.TaskStatus;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    void deleteTaskByTaskId(Long taskId);

    Task findTaskByTaskId(Long taskId);

    List<Task> getAllByOwnerAndTaskStatusAndCategory(Long owner, TaskStatus status, String category);
}
