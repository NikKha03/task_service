package sharpBubbles.taskTracker.DAO;

import org.springframework.stereotype.Repository;
import sharpBubbles.taskTracker.model.Task;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryTaskDAO {
    public final List<Task> TASKS = new ArrayList<>();


    public Task createNewTask(Task task) {
        TASKS.add(task);
        return task;
    }

    public String deleteTask(Task task) {
        TASKS.remove(task);
        return "Задача была успешно удалена";
    }

    public Task changeTask(Task task) {
        for (int i = 0; i < TASKS.size(); i++) {
            if (TASKS.get(i).getTaskId() == task.getTaskId()) {
                TASKS.remove(i);
                TASKS.add(task);
                break;
            }
        }
        return task;
    }

    public List<Task> getTasks() {
        return TASKS;
    }
}
