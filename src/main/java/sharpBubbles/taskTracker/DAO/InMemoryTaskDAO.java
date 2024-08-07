package sharpBubbles.taskTracker.DAO;

import org.springframework.stereotype.Repository;
import sharpBubbles.taskTracker.model.Task;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryTaskDAO {
    public final List<Task> TASKS = new ArrayList<>();

    public Task creatTask(Task task) {
        TASKS.add(task);
        return task;
    }

    public void deleteTask(Long taskId) {
        for (int i = 0; i < TASKS.size(); i++) {
            if (TASKS.get(i).getTaskId() == taskId) {
                TASKS.remove(i);
                break;
            }
        }
    }

    public Task changeTask(Task task) {
        for (int i = 0; i < TASKS.size(); i++) {
            if (TASKS.get(i).getTaskId() == task.getTaskId()) {
                if (task.getHeader() == null)
                    task.setHeader(TASKS.get(i).getHeader());
                if (task.getComment() == null)
                    task.setComment(TASKS.get(i).getComment());
                if (task.getDatePlannedImplementation() == null)
                    task.setDatePlannedImplementation(TASKS.get(i).getDatePlannedImplementation());
                TASKS.remove(i);
                TASKS.add(task);
                break;
            }
        }
        return task;
    }

    public List<Task> getAllTasks(Long owner) {
        List<Task> result = new ArrayList<>();
        for (int i = 0; i < TASKS.size(); i++) {
            if (TASKS.get(i).getOwner() == owner) {
                result.add(TASKS.get(i));
            }
        }
        return result;
    }

    public Task findTaskByTaskId(Long id) {
        for (int i = 0; i < TASKS.size(); i++) {
            if (TASKS.get(i).getTaskId() == id) {
                return TASKS.get(i);
            }
        }
        return null;
    }
}
