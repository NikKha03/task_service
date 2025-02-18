package NikKha03.TaskService.service.impl;

import NikKha03.TaskService.DTO.TaskRequest;
import NikKha03.TaskService.mappers.TaskMapper;
import NikKha03.TaskService.model.Category;
import NikKha03.TaskService.model.Task;
import NikKha03.TaskService.model.TaskStatus;
import NikKha03.TaskService.repository.CategoryRepository;
import NikKha03.TaskService.repository.TaskRepository;
import NikKha03.TaskService.service.TaskService;
import NikKha03.TaskService.service.builders.TaskBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;


@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;
    private final TaskMapper mapper;

    private final CategoryRepository categoryRepository;

    @Override
    public Task findTaskById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Task> getByCategory(Long categoryId) {
        return mapper.getTasksByCategory(categoryId);
    }

    @Override
    public List<Task> getTasksByStatus(String implementer, String status) {
        return mapper.getTasksByImplementerAndStatus(implementer, status);
    }

    @Override
    public List<Task> getInProgressTasks(String implementer) {
        List<Task> tasks = mapper.getTasksByImplementerAndStatus(implementer, TaskStatus.IN_PROGRESS.toString());
        return tasks.stream()
                .filter(task -> task.getPlannedImplDate() != null && task.getPlannedImplDate().toLocalDate().isAfter(LocalDate.now()))
                .sorted(Comparator.comparing(Task::getPlannedImplDate).reversed())
                .toList();
    }

    @Override
    public List<Task> getTasksIncomplete(String implementer) {
        List<Task> tasks = mapper.getTasksByImplementerAndStatus(implementer, TaskStatus.IN_PROGRESS.toString());
        return tasks.stream()
                .filter(task -> task.getPlannedImplDate() != null && task.getPlannedImplDate().toLocalDate().isBefore(LocalDate.now()))
                .sorted(Comparator.comparing(Task::getPlannedImplDate).reversed())
                .toList();
    }

    @Override
    public List<Task> getTaskWithoutDateImpl(String implementer) {
        return List.of();
    }

    @Override
    public Task createTask(String creator, TaskRequest request) {
        if (!categoryRepository.existsById(request.getCategoryId())) {
            //TODO
            return null;
        }
        Category category = categoryRepository.findById(request.getCategoryId()).orElse(null);

        TaskBuilder taskBuilder = new TaskBuilder()
                .setHeader(request.getHeader())
                .setComment(request.getComment())
                .setCreator(creator)
                .setCategory(category)
                .setCreationDate();

        if (request.getPlannedImplDate() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTimeOfTask = LocalDateTime.parse(request.getPlannedImplDate(), formatter);
            taskBuilder.setPlannedImplDate(dateTimeOfTask)
                    .setTaskStatus(TaskStatus.AWAITING_COMPLETION);
        } else {
            taskBuilder.setTaskStatus(TaskStatus.WITHOUT_DATE_IMPL);
        }

        return repository.save(taskBuilder.build());
    }

    @Override
    public void deleteTask(Long taskId) {
        repository.deleteById(taskId);
    }

    @Override
    public Task changeTask(Long taskId, TaskRequest request) {
        if (!repository.existsById(taskId) && !categoryRepository.existsById(request.getCategoryId())) {
            // TODO
            return null;
        }
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
            if (task.getTaskStatus().equals(TaskStatus.WITHOUT_DATE_IMPL)) {
                taskBuilder.setPlannedImplDate(dateTimeOfTask)
                        .setTaskStatus(TaskStatus.AWAITING_COMPLETION);
            }
        } else {
            taskBuilder.setPlannedImplDate(null)
                    .setTaskStatus(TaskStatus.WITHOUT_DATE_IMPL);
        }

        return repository.save(taskBuilder.build());
    }

    @Override
    public Task setStatus(Long taskId, TaskStatus status, boolean resetExecutionDate) {
        Task task = repository.findById(taskId).orElse(null);
        if (task == null) return null;

        if (resetExecutionDate) {
            task.setExecutionDate(null);
        } else {
            task.setExecutionDate(LocalDateTime.now());
        }

        task.setTaskStatus(status);

        return repository.save(task);
    }

    /*
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
     */

}
