package NikKha03.TaskService.service.builders;

import NikKha03.TaskService.model.Tab;
import NikKha03.TaskService.model.Task;
import NikKha03.TaskService.model.TaskStatus;

import java.time.LocalDateTime;

public class TaskBuilder {
    private final Task task;

    public TaskBuilder() {
        this.task = new Task();
    }

    public TaskBuilder(Task task) {
        this.task = task;
    }

    public TaskBuilder setHeader(String header) {
        task.setHeader(header);
        return this;
    }

    public TaskBuilder setComment(String comment) {
        task.setComment(comment);
        return this;
    }

    public TaskBuilder setCreator(String creator) {
        task.setCreator(creator);
        return this;
    }

    public TaskBuilder setTab(Tab tab) {
        task.setTab(tab);
        return this;
    }

    public TaskBuilder setDeadline(LocalDateTime deadline) {
        task.setDeadline(deadline);
        return this;
    }

    public TaskBuilder setCreationDate() {
        task.setCreationDate(LocalDateTime.now());
        return this;
    }

    public TaskBuilder setTaskStatus(TaskStatus taskStatus) {
        task.setTaskStatus(taskStatus);
        return this;
    }

    public Task build() {
        return task;
    }
}
