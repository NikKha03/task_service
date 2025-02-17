package NikKha03.TaskService.repository;

import NikKha03.TaskService.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Task findTaskByTaskId(Long taskId);

}
