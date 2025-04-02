package NikKha03.TaskService.service.impl;

import NikKha03.TaskService.DTO.TaskRequest;
import NikKha03.TaskService.mappers.TaskMapper;
import NikKha03.TaskService.model.Tab;
import NikKha03.TaskService.model.Task;
import NikKha03.TaskService.model.TaskStatus;
import NikKha03.TaskService.repository.TabRepository;
import NikKha03.TaskService.repository.TaskRepository;
import NikKha03.TaskService.service.TaskService;
import NikKha03.TaskService.service.builders.TaskBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    private final TabRepository tabRepository;

    @Override
    public Task findTaskById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Task> getByTab(Long tabId) {
        return mapper.getTasksByTab(tabId);
    }

    @Override
    public List<Task> getTasksByStatus(String implementer, String status) {
        return mapper.getTasksByImplementerAndStatus(implementer, status);
    }

    @Override
    public List<Task> getInProgressTasks(String implementer) {
        List<Task> tasks = mapper.getTasksByImplementerAndStatus(implementer, TaskStatus.IN_PROGRESS.toString());
        return tasks.stream()
                .filter(task -> task.getDeadline() != null && task.getDeadline().toLocalDate().isAfter(LocalDate.now()))
                .sorted(Comparator.comparing(Task::getDeadline).reversed())
                .toList();
    }

    @Override
    public List<Task> getTasksIncomplete(String implementer) {
        List<Task> tasks = mapper.getTasksByImplementerAndStatus(implementer, TaskStatus.IN_PROGRESS.toString());
        return tasks.stream()
                .filter(task -> task.getDeadline() != null && task.getDeadline().toLocalDate().isBefore(LocalDate.now()))
                .sorted(Comparator.comparing(Task::getDeadline).reversed())
                .toList();
    }

    @Override
    public List<Task> getTaskWithoutDateImpl(String implementer) {
        return List.of();
    }

    @Override
    public ResponseEntity<?> createTask(String creator, TaskRequest request) {
        if (!tabRepository.existsById(request.getTabId())) {
            return ResponseEntity.badRequest().body("Tab doesn't exist");
        }

        // TODO Не нравится, что надо доставать вкладку из бд
        Tab tab = tabRepository.findById(request.getTabId()).orElse(null);

        // TODO Добавить сохранение исполнителя
        TaskBuilder taskBuilder = new TaskBuilder()
                .setHeader(request.getHeader())
                .setComment(request.getComment())
                .setCreator(creator)
                .setTab(tab)
                .setCreationDate();

        if (request.getDeadline() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTimeOfTask = LocalDateTime.parse(request.getDeadline(), formatter);
            taskBuilder.setDeadline(dateTimeOfTask)
                    .setTaskStatus(TaskStatus.AWAITING_COMPLETION);
        } else {
            taskBuilder.setTaskStatus(TaskStatus.WITHOUT_DATE_IMPL);
        }

        return ResponseEntity.ok(repository.save(taskBuilder.build()));
    }

    @Override
    public void deleteTask(Long taskId) {
        repository.deleteById(taskId);
    }

    @Override
    public ResponseEntity<?> changeTask(Long taskId, TaskRequest request) {
        if (!repository.existsById(taskId) || !tabRepository.existsById(request.getTabId())) {
            return ResponseEntity.badRequest().body("Invalid taskId or TabId");
        }
        Task task = repository.findById(taskId).orElse(null);

        // TODO Добавить изменение исполнителя
        TaskBuilder taskBuilder = new TaskBuilder(task)
                .setHeader(request.getHeader())
                .setComment(request.getComment());

        if (request.getDeadline() != null && request.getDeadline().length() >= 10) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTimeOfTask = LocalDateTime.parse(request.getDeadline(), formatter);
            taskBuilder.setDeadline(dateTimeOfTask)
                    .setTaskStatus(TaskStatus.AWAITING_COMPLETION);
        } else {
            taskBuilder.setDeadline(null)
                    .setTaskStatus(TaskStatus.WITHOUT_DATE_IMPL);
        }

        return ResponseEntity.ok(repository.save(taskBuilder.build()));
    }

    @Override
    public Task setStatus(Long taskId, TaskStatus status, boolean resetExecutionDate) {
        // TODO Переписать на чистый SQL
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
