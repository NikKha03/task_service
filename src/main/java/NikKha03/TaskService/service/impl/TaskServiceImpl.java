package NikKha03.TaskService.service.impl;

import NikKha03.TaskService.DTO.TaskRequest;
import NikKha03.TaskService.model.Category;
import NikKha03.TaskService.model.Task;
import NikKha03.TaskService.model.TaskStatus;
import NikKha03.TaskService.repository.CategoryRepository;
import NikKha03.TaskService.repository.TaskRepository;
import NikKha03.TaskService.service.TaskService;
import NikKha03.TaskService.service.builders.TaskBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;
    private final CategoryRepository categoryRepository;

    /*
    @Override
    public List<Task> getCompletedTasks(Long owner, String category) {
        return repository.getAllByOwnerAndTaskStatusAndCategory(owner, TaskStatus.COMPLETED, category).stream()
                .sorted(Comparator.comparing(Task::getExecutionDate).reversed())
                .toList();
    }

    @Override
    public List<Task> getTasksOnTheDay(Long owner, String category) {
        List<Task> tasks = repository.getAllByOwnerAndTaskStatusAndCategory(owner, TaskStatus.IN_PROGRESS, category);

        return tasks.stream()
                .filter(task -> task.getPlannedImplDate() != null && LocalDate.now().equals(task.getPlannedImplDate().toLocalDate()))
                .sorted(Comparator.comparing(Task::getCreationDate).reversed())
                .toList();
    }

    @Override
    public List<Task> getTasksOnOtherDays(Long owner, String category) {
        List<Task> tasks = repository.getAllByOwnerAndTaskStatusAndCategory(owner, TaskStatus.IN_PROGRESS, category);

        return tasks.stream()
                .filter(task -> task.getPlannedImplDate() != null && task.getPlannedImplDate().toLocalDate().isAfter(LocalDate.now()))
                .sorted(Comparator.comparing(Task::getPlannedImplDate).reversed()
                        .thenComparing(Task::getCreationDate).reversed())
                .toList();
    }

    @Override
    public List<Task> getTasksIncomplete(Long owner, String category) {
        List<Task> tasks = repository.getAllByOwnerAndTaskStatusAndCategory(owner, TaskStatus.IN_PROGRESS, category);

        return tasks.stream()
                .filter(task -> task.getPlannedImplDate() != null && task.getPlannedImplDate().toLocalDate().isBefore(LocalDate.now()))
                .sorted(Comparator.comparing(Task::getPlannedImplDate).reversed())
                .toList();
    }

    @Override
    public List<Task> getInProgressTasksWithoutDatePlannedImplementation(Long owner, String category) {
        List<Task> tasks = repository.getAllByOwnerAndTaskStatusAndCategory(owner, TaskStatus.IN_PROGRESS, category);

        return tasks.stream()
                .filter(task -> task.getPlannedImplDate() == null)
                .sorted(Comparator.comparing(Task::getCreationDate).reversed())
                .toList();
    }
     */

    @Override
    public Task createTask(String owner, TaskRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId()).orElse(null);

        TaskBuilder taskBuilder = new TaskBuilder()
                .setHeader(request.getHeader())
                .setComment(request.getComment())
                .setOwner(owner)
                .setTaskStatus(TaskStatus.IN_PROGRESS)
                .setCategory(category)
                .setCreationDate();

        if (request.getPlannedImplDate() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTimeOfTask = LocalDateTime.parse(request.getPlannedImplDate(), formatter);
            taskBuilder.setPlannedImplDate(dateTimeOfTask);
        }

        return repository.save(taskBuilder.build());
    }

    @Override
    public void deleteTask(Long taskId) {
        repository.deleteTaskByTaskId(taskId);
    }

    @Override
    public Task changeTask(Long taskId, TaskRequest request) {
        Task task = repository.findById(taskId).orElse(null);
        Category category = categoryRepository.findById(request.getCategoryId()).orElse(null);

        TaskBuilder taskBuilder = new TaskBuilder(task)
                .setHeader(request.getHeader())
                .setComment(request.getComment())
                .setCategory(category)
                .setCreationDate();

        if (request.getPlannedImplDate() != null && request.getPlannedImplDate().length() >= 10) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTimeOfTask = LocalDateTime.parse(request.getPlannedImplDate(), formatter);
            taskBuilder.setPlannedImplDate(dateTimeOfTask);
        } else {
            taskBuilder.setPlannedImplDate(null);
        }

        return repository.save(taskBuilder.build());
    }

    public Task findTaskById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Task setStatusOnCompleted(Long taskId) {
        Task task = repository.findById(taskId).orElse(null);
        if (task == null) return null;

        task.setExecutionDate(LocalDateTime.now());
        task.setTaskStatus(TaskStatus.COMPLETED);

        return repository.save(task);
    }

    @Override
    public Task setStatusOnInProgress(Long taskId) {
        Task task = repository.findById(taskId).orElse(null);
        if (task == null) return null;

        task.setExecutionDate(null);
        task.setTaskStatus(TaskStatus.IN_PROGRESS);

        return repository.save(task);
    }

    @Override
    public Task setStatusOnAwaitingCompletion(Long taskId) {
        Task task = repository.findById(taskId).orElse(null);
        if (task == null) return null;

        task.setExecutionDate(null);
        task.setTaskStatus(TaskStatus.AWAITING_COMPLETION);

        return repository.save(task);
    }
}
