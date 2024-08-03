package sharpBubbles.taskTracker.model;

import java.time.LocalDateTime;

public class TaskBuilder {
    private final Task task = new Task();

    public TaskBuilder() {
    }

    public TaskBuilder setTaskId(Long taskId) {
        task.setTaskId(taskId);
        return this;
    }

    public TaskBuilder setHeader(String header) {
        task.setHeader(header);
        return this;
    }

    public TaskBuilder setComment(String comment) {
        task.setComment(comment);
        return this;
    }

    public TaskBuilder setOwner(Long owner) {
        task.setOwner(owner);
        return this;
    }

    public TaskBuilder setDateTimeOfTask(LocalDateTime dateTimeOfTask) {
        task.setDateTimeOfTask(dateTimeOfTask);
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
