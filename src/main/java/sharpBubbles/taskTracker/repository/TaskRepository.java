package sharpBubbles.taskTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sharpBubbles.taskTracker.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
